package net.peacefulcraft.trenchpvp.config;

import java.io.File;
import java.io.FilenameFilter;

public class YAMLFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".yml");
	}

}
