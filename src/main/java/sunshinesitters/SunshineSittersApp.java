package sunshinesitters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Runs a small, non-production demonstration of the matching workflow. */
public final class SunshineSittersApp {
    private SunshineSittersApp() {}

    public static void main(String[] args) {
        ParentRequest request = new ParentRequest(
                "Demo Parent", "parent@example.com", "Bellville", 180, 2, true);
        List<Babysitter> candidates = List.of(
                new Babysitter("Amina", "Bellville", 160, 3, true, true),
                new Babysitter("Thandi", "Parow", 140, 5, true, true),
                new Babysitter("Lebo", "Bellville", 190, 4, true, true),
                new Babysitter("Naledi", "Bellville", 150, 1, false, true));
        System.out.println("Sunshine Sitters — compatibility demo");
        BabysitterMatcher.rank(request, candidates).forEach(result ->
                System.out.printf("%s: %d points (%s)%n", result.babysitter().name(),
                        result.score(), String.join(", ", result.reasons())));
    }
}

record ParentRequest(String parentName, String email, String suburb,
                     double maximumHourlyRate, int minimumExperienceYears,
                     boolean certificationRequired) {
    ParentRequest {
        parentName = requireText(parentName, "parent name");
        email = requireText(email, "email");
        suburb = requireText(suburb, "suburb");
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
            throw new IllegalArgumentException("email address is invalid");
        if (maximumHourlyRate <= 0 || minimumExperienceYears < 0)
            throw new IllegalArgumentException("rate and experience must be valid");
    }
    private static String requireText(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " is required");
        return value.trim();
    }
}

record Babysitter(String name, String suburb, double hourlyRate,
                  int experienceYears, boolean certified, boolean available) {
    Babysitter {
        if (name == null || name.isBlank() || suburb == null || suburb.isBlank())
            throw new IllegalArgumentException("name and suburb are required");
        if (hourlyRate <= 0 || experienceYears < 0)
            throw new IllegalArgumentException("rate and experience must be valid");
        name = name.trim();
        suburb = suburb.trim();
    }
}

record MatchResult(Babysitter babysitter, int score, List<String> reasons) {
    MatchResult { reasons = List.copyOf(reasons); }
}

final class BabysitterMatcher {
    private BabysitterMatcher() {}
    static List<MatchResult> rank(ParentRequest request, List<Babysitter> candidates) {
        return candidates.stream()
                .filter(Babysitter::available)
                .filter(c -> !request.certificationRequired() || c.certified())
                .filter(c -> c.hourlyRate() <= request.maximumHourlyRate())
                .map(c -> score(request, c))
                .sorted(Comparator.comparingInt(MatchResult::score).reversed()
                        .thenComparing(r -> r.babysitter().name()))
                .toList();
    }
    private static MatchResult score(ParentRequest request, Babysitter candidate) {
        int score = 0;
        List<String> reasons = new ArrayList<>();
        if (candidate.suburb().equalsIgnoreCase(request.suburb())) {
            score += 50; reasons.add("same suburb");
        }
        if (candidate.experienceYears() >= request.minimumExperienceYears()) {
            score += 30; reasons.add("meets experience preference");
        }
        if (candidate.certified()) { score += 15; reasons.add("certified"); }
        if (request.maximumHourlyRate() - candidate.hourlyRate()
                >= request.maximumHourlyRate() * 0.15) {
            score += 5; reasons.add("comfortably within budget");
        }
        return new MatchResult(candidate, score, reasons);
    }
}
