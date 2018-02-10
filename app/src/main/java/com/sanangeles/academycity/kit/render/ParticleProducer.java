package com.sanangeles.academycity.kit.render;

import com.sanangeles.academycity.*;
import com.sanangeles.academycity.kit.*;

public class ParticleProducer
{
	public final static void spawn(int type, double x, double y, double z, double speedX, double speedY, double speedZ, int size) {
		String source = Runner.wrapper(GameData.getClassName(), "addParticle", type, x, y, z, speedX, speedY, speedZ, size);
		//Launcher.displayDialog(source);
		Launcher.header.evaluate(source);
	}
}
