package com.bidx.util;
import java.nio.file.*;
public final class AppPaths { private AppPaths(){} public static Path data(){String p=System.getProperty("bidx.data.dir", "./data"); try{return Files.createDirectories(Path.of(p));}catch(Exception e){throw new IllegalStateException("Cannot initialize data folder",e);}} public static Path file(String n){return data().resolve(n);} }
