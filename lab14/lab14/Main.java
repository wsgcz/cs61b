package lab14;

import lab14lib.*;

import java.util.ArrayList;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
//		Generator g1 = new SawToothGenerator(512);
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g1);
//		gav.drawAndPlay(4096, 1000000);
//		Generator g1 = new AcceleratingSawToothGenerator(200, 1.1);
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g1);
//		gav.drawAndPlay(4096, 1000000);
		Generator g1 = new StrangeBitwiseGenerator(512);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g1);
		gav.drawAndPlay(4096, 1000000);
	}
} 