# Manual QA cases

| ID | Module | Scenario | Expected result |
|---|---|---|---|
| AUTH-01 | Authentication | Register valid user | Account persists with hash, then sign-in succeeds |
| AUTH-02 | Authentication | Repeat email/username | Friendly duplicate error; no new user |
| AUTH-03 | Authentication | Open dashboard signed out | Redirected to sign in |
| AUC-01 | Auction | Create valid future auction | Auction appears in My Auctions |
| AUC-02 | Auction | End before start | Validation error; no auction stored |
| BID-01 | Bidding | Minimum valid bid | Bid history and current bid update |
| BID-02 | Bidding | Bid below increment | Friendly rejection; XML unchanged |
| BID-03 | Bidding | Seller bids own auction | Rejected |
| BID-04 | Bidding | Bid after close | Rejected |
| WATCH-01 | Watchlist | Add/remove same auction | Item toggles without duplicate |
| UI-01 | UI | Toggle theme and refresh | Theme remains readable and selected |
| XML-01 | Storage | Restart Tomcat | Existing users, auctions, and bids remain |
