package sunshinesitters;

import java.util.List;

/** Dependency-free test runner. Run with assertions enabled (-ea). */
public final class BabysitterMatcherTest {
    public static void main(String[] args) {
        ParentRequest request = new ParentRequest(
                "Parent", "parent@example.com", "Bellville", 180, 2, true);
        Babysitter best = new Babysitter("Best", "Bellville", 140, 4, true, true);
        Babysitter other = new Babysitter("Other", "Parow", 170, 2, true, true);
        Babysitter unavailable = new Babysitter("Unavailable", "Bellville", 100, 8, true, false);
        Babysitter uncertified = new Babysitter("Uncertified", "Bellville", 100, 8, false, true);
        List<MatchResult> results = BabysitterMatcher.rank(
                request, List.of(other, unavailable, best, uncertified));
        assert results.size() == 2 : "ineligible candidates must be excluded";
        assert results.get(0).babysitter().equals(best) : "highest score must rank first";
        assert results.get(0).score() == 100 : "expected transparent score of 100";
        expectInvalid(() -> new ParentRequest("", "bad-email", "", -1, -1, false));
        expectInvalid(() -> new Babysitter("", "", 0, -1, false, false));
        System.out.println("All BabysitterMatcher tests passed.");
    }
    private static void expectInvalid(Runnable action) {
        try { action.run(); throw new AssertionError("expected validation failure"); }
        catch (IllegalArgumentException expected) { /* expected */ }
    }
}
