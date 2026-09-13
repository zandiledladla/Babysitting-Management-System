# Product scope

The original wireframes explored parent and babysitter dashboards, child profiles, messaging, emergency information, payments and reporting. Those screens were design concepts, not implemented features.

This repository implements a smaller proof of concept: validate a parent's search, exclude unavailable or unsuitable candidates, rank eligible babysitters using explicit criteria, and explain each score.

| Criterion | Points |
|---|---:|
| Same suburb | 50 |
| Meets minimum experience | 30 |
| Holds a certification | 15 |
| At least 15% below maximum rate | 5 |

The rules are deliberately visible rather than presented as AI. A real service would require identity checks, safeguarding policies, consent-based handling of child data, secure authentication and legal review.
