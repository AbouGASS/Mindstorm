
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class rotation {


	public static int [][] color1=new int[100][3];
	public static int [][] color2=new int[100][3];
	public static int [][] color3=new int[100][3];
	public static int[] moy1=new int[3];
	public static int[] moy2=new int[3];
	public static int[] moy3=new int[3];
	public static ColorSensor cs = new ColorSensor(SensorPort.S3);
	public static lejos.robotics.Color color;
	public static DifferentialPilot pilot;
	public static double distanceTo(int[] moy,int[] cc) {
	    return Math.sqrt(Math.pow(moy[0] - cc[0], 2) + Math.pow(moy[1] - cc[1], 2) + Math.pow(moy[2] - cc[2], 2));
	}
	public static int[] moyenne(int [][] col)
	{
		int sr=0;
		int sg=0;
		int sb=0;
		int[] moy=new int[3];
		for(int i = 0; i <100; i++){
			
	       sr=sr+col[i][0];
	       sg=sg+col[i][1];
	       sb=sb+col[i][2];
		}
		
			moy[0]=(int)sr/100;
			moy[1]=(int)sg/100;
			moy[2]=(int)sb/100;
		return moy;
	}
	
	public static int captureCouleur(){
		  moy1=moyenne(color1);
	        moy2=moyenne(color2);
	        moy3=moyenne(color3);
	        System.out.println("Test color");
	     //   Button.waitForAnyPress();
	              
	       // for(int i = 0; i < 5; i++) {
	        	color = cs.getColor();
	            int[] cc=new int[3];
	            cc[0]=color.getRed();
	            cc[1]=color.getGreen();
	            cc[2]=color.getBlue();
	            
	            double col1=distanceTo(moy1,cc);
	            double col2=distanceTo(moy2,cc);
	            double col3=distanceTo(moy3,cc);
	            
	        if(col1< col2 && col1<col3){
	        	 System.out.println("Color 1 visualiser");
	        	 return 1;
	        }else if(col2< col1 && col2<col3 ){
	        	System.out.println("Color 2 visualiser");
	        	return 2;
	        }else if(col3< col1 && col3<col2  ){
	        	System.out.println("Color 3 visualiser");
	        	return 3;
	        }else{
	        	System.out.println("errooor");
	        	return 0;
	        }
	        
	 //       Button.waitForAnyPress();
	       // }
	       
	        
	        
		}
		
	
	public static  void appCol(){
			
		System.out.println("Color = 1");
		Button.waitForAnyPress();
		
        for(int i = 0; i < 100; i++) {
        	System.out.println("Color1"+" Test "+i);
        //	Button.waitForAnyPress();
            color = cs.getColor();
            color1[i][0]= color.getBlue();
            color1[i][1]= color.getRed();
            color1[i][2]= color.getGreen();
          
        }       
        System.out.println("Color = 2");
		Button.waitForAnyPress();
        for(int i = 0; i < 100; i++) {
        	System.out.println("Color2"+" Test "+i);
        	//Button.waitForAnyPress();
        	 color = cs.getColor();
             color2[i][0]= color.getBlue();
             color2[i][1]= color.getRed();
             color2[i][2]= color.getGreen();
        }       
        System.out.println("Color = 3");
		Button.waitForAnyPress();
        for(int i = 0; i < 100; i++) {
        	System.out.println("Color3"+" Test "+i);
        //	Button.waitForAnyPress();
        	 color = cs.getColor();
             color3[i][0]= color.getBlue();
             color3[i][1]= color.getRed();
             color3[i][2]= color.getGreen();
          
        }    
		
		
	}
	public static void travel(){

				//pilot.forward();
	   			Motor.A.forward();
	   			Motor.B.forward();
	   			//Motor.C.forward();
	}
	
	
	public static void arreter(){
		//pilot.stop();
		Motor.A.stop();
		Motor.B.stop();
		//Motor.C.flt();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Apprentissage!!!!!");
		Button.waitForAnyPress();
		appCol();
		System.out.println("Forward while coleur 1");
		Button.waitForAnyPress();
//		boolean rotated_updated = true;
		int rotation_side = 1;
		int angle=15 ;
		//int i=0;
		//boolean update_turn = true;
		/**
		 * Start following the line
		 */
		
		 //pilot = new DifferentialPilot(1.5f,5.1f, Motor.A, Motor.B);
		displayFull("Start following\nthe line ...");
		do{
			if(captureCouleur()== 1){
					travel();
					angle=15;
					
				}
			else{
				arreter();
				if(rotation_side==1){
					Motor.B.rotate(angle,false);
					Motor.A.stop();
					rotation_side=-1;
					if(captureCouleur()== 1){
							travel();
							angle=15;
							//rotation_side=-1;
						}
					else{
						Motor.B.stop();
						Motor.A.rotate(3*angle,false);
						rotation_side=1;
						if(captureCouleur()== 1){
							travel();
							angle=15;
							//rotation_side=1;
						}
						
						}
					}
					
			
				else{
					Motor.A.rotate(angle,false);
					Motor.B.stop();
					rotation_side=1;
					if(captureCouleur()== 1){
							travel();
							angle=15;
							//rotation_side=1;

						}
					else{
						Motor.B.rotate(3*angle,false);
						Motor.A.stop();	
						rotation_side=-1;
						if(captureCouleur()== 1){
							travel();
							angle=15;

						}
						
					}
					
				}
				
			}
			
			angle+=5;
			//i++;
			System.out.println("Angle "+angle);
		}while(!Button.ESCAPE.isDown());
		Button.waitForAnyPress();
		
   	
       
      
	}
	public static void displayFull(String phrase){
		LCD.clear();
		LCD.drawString(phrase, 0, 0);
	}
}