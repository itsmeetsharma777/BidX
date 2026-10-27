package com.bidx.util; import java.util.concurrent.atomic.AtomicLong;
public final class IdGenerator { private static final AtomicLong SEQ=new AtomicLong(System.currentTimeMillis()%1_000_000); private IdGenerator(){} public static String next(String prefix){return prefix+"-"+SEQ.incrementAndGet();} }
