package com.bidx.repository;

import com.bidx.model.*;
import com.bidx.util.*;
import org.w3c.dom.*;
import java.nio.file.*;
import java.time.*;
import java.math.*;
import java.util.*;

public class BidRepository {
    private final Path p = AppPaths.file("bids.xml");

    public synchronized List<Bid> all() {
        Document d = XmlStore.load(p, "bids");
        List<Bid> r = new ArrayList<>();
        NodeList ns = d.getElementsByTagName("bid");
        for (int i = 0; i < ns.getLength(); i++) {
            Element e = (Element) ns.item(i);
            Bid b = new Bid();
            b.id = XmlStore.text(e, "id");
            b.auctionId = XmlStore.text(e, "auctionId");
            b.bidderId = XmlStore.text(e, "bidderId");
            b.amount = new BigDecimal(XmlStore.text(e, "amount"));
            b.createdAt = LocalDateTime.parse(XmlStore.text(e, "createdAt"));
            r.add(b);
        }
        return r;
    }

    public synchronized void save(Bid b) {
        Document d = XmlStore.load(p, "bids");
        Element e = d.createElement("bid");
        for (Object[] x : new Object[][] { { "id", b.id }, { "auctionId", b.auctionId }, { "bidderId", b.bidderId },
                { "amount", b.amount }, { "createdAt", b.createdAt } })
            XmlStore.put(d, e, (String) x[0], x[1]);
        d.getDocumentElement().appendChild(e);
        XmlStore.save(d, p);
    }

    public List<Bid> forAuction(String id) {
        return all().stream().filter(b -> b.auctionId.equals(id))
                .sorted(Comparator.comparing((Bid b) -> b.createdAt).reversed()).toList();
    }

    public List<Bid> forUser(String id) {
        return all().stream().filter(b -> b.bidderId.equals(id))
                .sorted(Comparator.comparing((Bid b) -> b.createdAt).reversed()).toList();
    }
}
