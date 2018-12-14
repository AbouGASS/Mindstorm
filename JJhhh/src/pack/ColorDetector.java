package pack;
import lejos.nxt.*;

public class ColorDetector {
	public static void main(String [] args)
	throws Exception
	{
	int deg;
	LightSensor light = new LightSensor(SensorPort.S2); 
	// seuil lumineux = 40
	// 10 tours = 28300 degrés
	Motor.B.regulateSpeed(false); //init des moteurs
	Motor.A.regulateSpeed(false);
	Motor.C.regulateSpeed(false);
	Motor.B.resetTachoCount(); //reset compteurs de tours
	Motor.C.resetTachoCount();
	Motor.A.resetTachoCount();
	deg = 0;
	Motor.B.backward(); // on démarre...
	Motor.C.backward();
	  
	while ( deg < 28300 && (Button.readButtons() == 0) ) // tant que pas 10 tours
	    {
	    if ( light.readValue() > 40 ) // pas de ligne
	        {// tourne à droite
	        Motor.B.setPower(10);
	        Motor.C.setPower(40);
	        }
	    else // sur la ligne
	        { // tourne à gauche
	        Motor.B.setPower(40);
	        Motor.C.setPower(15);
	        }
	    deg = 0 - Motor.B.getTachoCount() - Motor.C.getTachoCount();
	    }
	LCD.drawInt(Motor.B.getTachoCount(), 4, 0, 0);
	LCD.drawInt(Motor.C.getTachoCount(), 4, 0, 1);
	Motor.B.stop();
	Motor.C.stop();
	Thread.sleep(5000);
	}
}
