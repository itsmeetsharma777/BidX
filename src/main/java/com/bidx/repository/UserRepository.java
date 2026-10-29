package com.bidx.repository;

import com.bidx.model.*;
import com.bidx.util.*;
import org.w3c.dom.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class UserRepository {
    private final Path p = AppPaths.file("users.xml");

    public synchronized List<User> all() {
        Document d = XmlStore.load(p, "users");
        List<User> r = new ArrayList<>();
        NodeList ns = d.getElementsByTagName("user");
        for (int i = 0; i < ns.getLength(); i++) {
            Element e = (Element) ns.item(i);
            User u = new User();
            u.id = XmlStore.text(e, "id");
            u.name = XmlStore.text(e, "name");
            u.username = XmlStore.text(e, "username");
            u.email = XmlStore.text(e, "email");
            u.phone = XmlStore.text(e, "phone");
            u.passwordHash = XmlStore.text(e, "passwordHash");
            u.role = Role.valueOf(XmlStore.text(e, "role"));
            u.status = XmlStore.text(e, "status");
            u.createdAt = LocalDateTime.parse(XmlStore.text(e, "createdAt"));
            r.add(u);
        }
        return r;
    }

    public synchronized void save(User u) {
        Document d = XmlStore.load(p, "users");
        Element e = d.createElement("user");
        for (Object[] x : new Object[][] { { "id", u.id }, { "name", u.name }, { "username", u.username },
                { "email", u.email }, { "phone", u.phone }, { "passwordHash", u.passwordHash }, { "role", u.role },
                { "createdAt", u.createdAt }, { "status", u.status } })
            XmlStore.put(d, e, (String) x[0], x[1]);
        d.getDocumentElement().appendChild(e);
        XmlStore.save(d, p);
    }

    public User findByLogin(String s) {
        return all().stream().filter(u -> u.username.equalsIgnoreCase(s) || u.email.equalsIgnoreCase(s)).findFirst()
                .orElse(null);
    }

    public User find(String id) {
        return all().stream().filter(u -> u.id.equals(id)).findFirst().orElse(null);
    }
}
