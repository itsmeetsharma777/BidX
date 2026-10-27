# BidX diagrams

```mermaid
flowchart LR
Browser --> JSP[JSP views] --> Servlet[Servlet controllers] --> Service[Services] --> Repo[XML repositories] --> XML[(XML files)]
Servlet --> Filter[Authentication filter]
```

```mermaid
sequenceDiagram
participant B as Bidder
participant S as PlaceBidServlet
participant V as BidService
participant X as XML repositories
B->>S: POST amount + auction id
S->>V: place()
V->>X: read auction; validate status/owner/minimum
V->>X: append bid and update highest bid
V-->>S: success or friendly validation error
S-->>B: redirect to auction details
```

```mermaid
stateDiagram-v2
[*] --> UPCOMING
UPCOMING --> ACTIVE: start time
ACTIVE --> ENDING_SOON: < 30 minutes
ENDING_SOON --> COMPLETED: end time + bid
ACTIVE --> CLOSED: end time + no bid
ENDING_SOON --> CLOSED: end time + no bid
UPCOMING --> CANCELLED
ACTIVE --> CANCELLED
```
