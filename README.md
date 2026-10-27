# BidX — Online Auction System

BidX is a BE CSE/IT PBL mini-project: a local online auction marketplace built with Java 21, Jakarta Servlets, JSP, vanilla JavaScript, and XML. It follows MVC: JSP views call servlet controllers, which use service-layer business rules and safe XML repositories.

## Features

- Registration and login with PBKDF2-HMAC-SHA256 password hashes and `HttpSession`
- Browse, server-side search/filter/sort, auction details, time-based lifecycle, and bid history
- Validated auction creation, serialized bid placement, XML persistence, watchlist, seller and bidder dashboards
- Responsive light/dark interface; the selected theme persists in browser storage
- Demo data on first startup: `admin / Admin@123`, `seller / Seller@123`, `bidder / Bidder@123`

## Run on macOS

1. Install JDK 21, Apache Maven, and Tomcat 10.1.
2. From this directory run `mvn clean package`.
3. Copy `target/bidx.war` to Tomcat's `webapps/` directory and start Tomcat.
4. Open [http://localhost:8080/bidx](http://localhost:8080/bidx).

The data directory defaults to `./data`. Override it when starting Tomcat with `-Dbidx.data.dir=/absolute/path/to/data`; XML writes use a temporary file and atomic replacement where supported. Time is evaluated using the server's local time; use one consistent machine timezone during a demonstration.

## Security and scope

Passwords are never written in clear text. XML parsing disables DTD/entity expansion. Authenticated actions are filter-protected; bid validation always runs on the server and a single JVM lock serializes bid updates. This educational version does not yet include image upload, CSRF tokens, automated email, payment/transactions, or a complete administrative category-management UI.

## Project layout

`src/main/java/com/bidx` contains models, repositories, services, servlets, filters, and utilities. JSP views and assets reside in `src/main/webapp`; XML resides in `data`; test planning and architecture diagrams are in `docs`.

## Demo walkthrough

Open the home page, change theme, sign in as `bidder`, browse the active camera auction, submit a bid of at least the displayed minimum, then open **My Bids**. Sign in as `seller` to create an auction and open **My Auctions**. Explain that `data/bids.xml` records the accepted bid and `data/auctions.xml` reflects the current highest bid.
