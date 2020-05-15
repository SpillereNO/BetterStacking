package net.hydrotekz.BetterStacking.support;

import net.hydrotekz.BetterStacking.StackingPlugin;

public abstract class MultiVersion implements VersionSupport {

	private static MultiVersion multiVersion;

	public static MultiVersion get() {
		if (multiVersion != null) {
			return multiVersion;
		}
		else {
			if (StackingPlugin.mc_version >= 19) {
				multiVersion = new mc_newer();
				return multiVersion;
			}
			else {
				multiVersion = new mc_legacy();
				return multiVersion;
			}
		}
	}
}