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
	
	public final static void circle(int type, Group<Double> pos, Group<Double> speed, int size, float space) {
		for(int i = 0; i < 360; ++i) {
			double Fx = Math.sin(i * 1 / 180 * Math.PI) * space;
			double Fz = Math.cos(i * 1 / 180 * Math.PI) * space;
			spawn(type, (Double)pos.get(0)+Fx, (Double)pos.get(1), ((Double)pos.get(2))+Fz, (Double)speed.get(0), (Double)speed.get(1), (Double)speed.get(2), size);
		}
	}
}
