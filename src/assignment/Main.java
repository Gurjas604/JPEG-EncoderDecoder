package assignment;


import java.io.File;
import java.io.IOException;

import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.*;

public class Main extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	//DCT matrix 
	public static  Matrix T = new Matrix(8,8);
	
	//GUI buttons and images 
	JButton m_btOpen, m_btSave, m_btConvert; 
	IMGPanel  m_panelImgInput, m_panelImgOutput, m_panelImgIntermediateYUV, m_panelImgOutputDCT;
	BufferedImage m_imgInput, m_imgOutput, m_imgIntermeadiateYUV, m_imgIntermeadiateDCT;
	
	//Used to read and write jpg files
	final JFileChooser fc = new JFileChooser();

	public static void main(String[] args)throws IOException
		{
       
		T = DCTMaker(T);
			
		//Calls GUI builder
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });	
	}
	
	public static Matrix DCTmatrix(Matrix X)
	{
		Matrix trans = T.transpose();
		
		X = MatrixMul(X, trans);
		X = MatrixMul(T, X);
		
	return X;
	}
	
	public static Matrix UNDCTmatrix(Matrix X)
	{
		Matrix trans = T.transpose();
		
		X = MatrixMul(X, T);
		X = MatrixMul(trans, X);
	
	return X;
	}
	
	
	public static Matrix MatrixMul(Matrix X, Matrix Y)
	{
     
        Matrix C = new Matrix(8,8);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                for (int k = 0; k < 8; k++)
                    C.data[i][j] += (X.data[i][k] * Y.data[k][j]);
        return C;
	}
	
  
    
	public static Matrix DCTMaker (Matrix block)
	{
		double[][] table = new double [8][8];
		
		for(int i = 0; i < 8; i++)
		{
			table[0][i]= 1/(2*Math.sqrt(2));
		}
		
		for(int j = 1 ; j<8; j++)
		{
			for(int i = 0; i < 8; i++)
			{
				table[j][i]= ((Math.cos(((1+i*2)*j*(Math.PI))/16))/2);
			}
		}
		block.data = table;
				
		return block;
	}
    
	public Matrix lum(Matrix block, double q){
		
		double[][] table = {
				{16,11,10,16,24,40,51,61},
				{12,12,14,19,26,58,60,55},
				{14,13,16,24,40,57,69,56},
				{14,17,22,29,51,87,80,62},
				{18,22,37,56,68,109,103,77},
				{24,35,55,64,81,104,113,92},
				{49,64,78,87,103,121,120,101},
				{72,92,95,98,112,100,103,99}
		};
			
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				block.data[y][x] = Math.round(block.data[y][x] / (table[y][x]*q));
			}
		}
		
		return block;
		
	}
	
	public Matrix unlum(Matrix block, double q){
		
	
		double[][] table = {
				{16,11,10,16,24,40,51,61},
				{12,12,14,19,26,58,60,55},
				{14,13,16,24,40,57,69,56},
				{14,17,22,29,51,87,80,62},
				{18,22,37,56,68,109,103,77},
				{24,35,55,64,81,104,113,92},
				{49,64,78,87,103,121,120,101},
				{72,92,95,98,112,100,103,99}
		};
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				block.data[y][x] = Math.round(block.data[y][x] * (table[y][x]*q));
			}
		}
		
		return block;
		
	}
	
	public Matrix chrom(Matrix block, double q){

	double[][] table = {
				
				{17,18,24,47,99,99,99,99},
				{18,21,26,66,99,99,99,99},
				{24,26,56,99,99,99,99,99},
				{47,66,99,99,99,99,99,99},
				{99,99,99,99,99,99,99,99},
				{99,99,99,99,99,99,99,99},
				{99,99,99,99,99,99,99,99},
				{99,99,99,99,99,99,99,99}
		
		};
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				block.data[y][x] = Math.round(block.data[y][x] /(table[y][x]*q));
			}
		}
		
		return block;
		
	}
	
	public Matrix unchrom(Matrix block, double q){
		

		
		double[][] table = {
					
					{17,18,24,47,99,99,99,99},
					{18,21,26,66,99,99,99,99},
					{24,26,56,99,99,99,99,99},
					{47,66,99,99,99,99,99,99},
					{99,99,99,99,99,99,99,99},
					{99,99,99,99,99,99,99,99},
					{99,99,99,99,99,99,99,99},
					{99,99,99,99,99,99,99,99}
			
			};
			
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					block.data[y][x] = Math.round(block.data[y][x] * (table[y][x]*q));
				}
			}
			
			return block;
			
		}
	
		
	  private static void createAndShowGUI(){
	        JFrame.setDefaultLookAndFeelDecorated(true);
	        JFrame frame = new JFrame("JEPG Demo");

	        //Create and set up the content pane.
	        Main demo = new Main();
	        frame.setContentPane(demo.createContentPane());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1200, 800);
	        frame.setVisible(true);
	    }
	
	  public JPanel createContentPane (){	    
	    
		// We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        
        //Bunch of buttons
	    m_btOpen = new JButton("Open");
	    m_btOpen.setLocation(440, 10);
	    m_btOpen.setSize(100, 40);
	    m_btOpen.addActionListener(this);
	    totalGUI.add(m_btOpen);
	    
	    m_btConvert = new JButton("Convert");
	    m_btConvert.setLocation(550, 10);
	    m_btConvert.setSize(100, 40);
	    m_btConvert.addActionListener(this);
	    totalGUI.add(m_btConvert);
	    
	    m_btSave = new JButton("Save");
	    m_btSave.setLocation(660, 10);
	    m_btSave.setSize(100, 40);
	    m_btSave.addActionListener(this);
	    totalGUI.add(m_btSave);
	    
	    //Top left Original Image
	    m_panelImgInput = new IMGPanel();
	    m_panelImgInput.setLocation(10, 50);
	    m_panelImgInput.setSize(585, 365);
	    totalGUI.add(m_panelImgInput);
	    
	    //top right YUV image
	    m_panelImgIntermediateYUV = new IMGPanel(); 
	    m_panelImgIntermediateYUV.setLocation(605, 50);
	    m_panelImgIntermediateYUV.setSize(585, 365);
	    totalGUI.add(m_panelImgIntermediateYUV);
	    
	    //bottom left DCT image
	    m_panelImgOutputDCT = new IMGPanel();
	    m_panelImgOutputDCT.setLocation(10, 425);
	    m_panelImgOutputDCT.setSize(585, 365);
	    totalGUI.add(m_panelImgOutputDCT);
         
	    //bottom right final image
        m_panelImgOutput = new IMGPanel();
        m_panelImgOutput.setLocation(605, 425);
        m_panelImgOutput.setSize(585, 365);
        totalGUI.add(m_panelImgOutput);
   	    
	    totalGUI.setOpaque(true);
	    return totalGUI;
	}
	  
	public void actionPerformed(ActionEvent evnt)
	{
		//File selecting menu 
	    if(evnt.getSource() == m_btOpen)
	    {
	    	fc.addChoosableFileFilter(new ImageFilter());
	        fc.setAcceptAllFileFilterUsed(false);
	        int returnVal = fc.showOpenDialog(Main.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File file = fc.getSelectedFile();
	        	try 
	        	{
	                m_imgInput = ImageIO.read(file);
	                m_imgIntermeadiateYUV = ImageIO.read(file);
	                m_imgIntermeadiateDCT = ImageIO.read(file);
	                m_imgOutput = ImageIO.read(file);
	                
	                m_panelImgInput.setBufferedImage(m_imgInput);	
	            }
	        	catch (IOException ex)
	            {
	                	
	            }
	        }
	    }
	    	
	    //All the DCT, quantization and subsampling happens over here
	    else if(evnt.getSource() == m_btConvert)
	    {
	        if(m_imgInput == null)
	        {
	        	return;
	        }
	        	
	        	int width = m_imgInput.getWidth();
        		int height = m_imgInput.getHeight();
        		
        		Matrix Alpha = new Matrix(height, width);
        		Matrix Red = new Matrix(height, width);
        		Matrix Green = new Matrix(height, width);
        		Matrix Blue = new Matrix(height, width);
        		
        		int P, A, R, G, B;
        		double Y, U, V;
		
				
        		for(int y = 0; y < height; y++) 
        		{
        			for(int x = 0; x < width; x++) 
        			{
        				P = m_imgInput.getRGB(x, y);
        				A = (P>>24) & 0xff; 
        				R = (P>>16) & 0xff;
        				G = (P>>8) & 0xff;
        				B = P & 0xff;
        				
        				Alpha.data[y][x] = (double) A;
        				Red.data[y][x] = (double) R;
        				Green.data[y][x] = (double) G;
        				Blue.data[y][x] = (double) B;
        				
        			}
        		}
        		
        		for(int y = 0; y < height; y++) {
        			for(int x = 0; x < width; x++) {
        				
        				
        				Y = (0.299*Red.data[y][x] + 0.587*Green.data[y][x] + 0.114*Blue.data[y][x]);
        				U = (-0.14713*Red.data[y][x] + -0.28886*Green.data[y][x] + 0.436*Blue.data[y][x]);
        				V = (0.615*Red.data[y][x] + -0.51499*Green.data[y][x] + -0.1001*Blue.data[y][x]);

        				Red.data[y][x] = Y;
        				Green.data[y][x] = U;
        				Blue.data[y][x] = V;
        			}
        		}
        		//4.2.0 Subsampling 
        		//*****************************Causing Noise*****************
        		for(int y = 0; y<height; y+=2) {
        			for(int x = 0; x<width; x+=2) {
        				
        				U = Green.data[y][x] + Green.data[y][x+1]+ Green.data[y+1][x]+ Green.data[y+1][x+1];
        				V = Blue.data[y][x]+ Blue.data[y][x+1]+ Blue.data[y+1][x]+ Blue.data[y+1][x+1];
        				U = U/4;
        				V = V/4;
        				
        				Green.data[y][x] = U;
        				Green.data[y][x+1] = U;
        				Green.data[y+1][x] = U;
        				Green.data[y+1][x+1] = U;
        				
        				Blue.data[y][x] = V;
        				Blue.data[y][x+1] = V;
        				Blue.data[y+1][x] = V;
        				Blue.data[y+1][x+1] = V;

        			}
        		}
        		//************************************************************
        		for(int y = 0; y < height; y++) {
        			for(int x = 0; x < width; x++) {
        				
        				A = (int) Alpha.data[y][x];
        				R = (int) Red.data[y][x];
        				
        				P = 0;
        				P = (A<<24) | (R<<16)| (R<<8)| (R<<0) ;
        				
        				m_imgIntermeadiateYUV.setRGB(x, y, P);
        			}
        		}
        		
        		m_panelImgIntermediateYUV.setBufferedImage(m_imgIntermeadiateYUV);
        		
        		for(int y = 0; y < height; y++) {
        			for(int x = 0; x < width; x++) {
        				
        				A = (int) Alpha.data[y][x];
        				G = (int) Green.data[y][x] + 128;
        				B = (int) Blue.data[y][x] + 128;
        				
        				P = 0;
        				P = (A<<24) | (G<<8)| (B<<0);
        				
        				m_imgIntermeadiateDCT.setRGB(x, y, P);
        			}
        		}
        		
           		m_panelImgOutputDCT.setBufferedImage(m_imgIntermeadiateDCT);
           		
           	
        		Matrix DCTY = new Matrix(8,8);
        		Matrix DCTU = new Matrix(8,8);
        		Matrix DCTV = new Matrix(8,8);
        		
        		double value = 50;
        		double compression = value/50;
        		
        		for(int a = 0; a < (int) height/8  ; a++)
        		{
        			for(int b = 0; b < (int) width/8  ; b++)
        			{
        				for(int i = 0 ; i< 8 ; i++)
        				{
        					for(int j = 0 ; j < 8 ; j++)
        					{
     		
        						DCTY.data[i][j] = Red.data[(a*8)+i][(b*8)+j];
        						DCTU.data[i][j] = Green.data[(a*8)+i][(b*8)+j];
        						DCTV.data[i][j] = Blue.data[(a*8)+i][(b*8)+j];
        					}
        				}
        				
        				DCTY = DCTmatrix(DCTY);
        				DCTU = DCTmatrix(DCTU);
        				DCTV = DCTmatrix(DCTV);
        				
        				DCTY = lum(DCTY, compression);
        				DCTU = chrom(DCTU, compression);
        				DCTV = chrom(DCTV, compression);
        				
//        				******** This code can be used to demonstarate DCT working*******
//        				for(int i = 0 ; i< 8 ; i++)
//        				{
//        					for(int j = 0 ; j < 8 ; j++)
//        					{
//        						A = (int) (Alpha.data[(a*8)+i][(b*8)+j]);
//        						R = (int) (DCTY.data[i][j]); 
//        						G = (int) (DCTU.data[i][j]); 
//        						B = (int) (DCTV.data[i][j]);
//        						
//        						P = 0;
//                				P = (A<<24) | (R<<16) | (G<<8) | B;
//        						
//                				m_imgIntermeadiateDCT.setRGB((b*8)+j , (a*8)+i , P);
//        					}
//        				}
        				DCTY = unlum(DCTY, compression);
        				DCTU = unchrom(DCTU, compression);
        				DCTV = unchrom(DCTV, compression);
        				
        				DCTY = UNDCTmatrix(DCTY);
        				DCTU = UNDCTmatrix(DCTU);
        				DCTV = UNDCTmatrix(DCTV);
        				
        				for(int i = 0 ; i< 8 ; i++)
        				{
        					for(int j = 0 ; j < 8 ; j++)
        					{
        						
        						Red.data[(a*8)+i][(b*8)+j] = DCTY.data[i][j] ;
        						Green.data[(a*8)+i][(b*8)+j] = DCTU.data[i][j] ;
        						Blue.data[(a*8)+i][(b*8)+j] = DCTV.data[i][j];
        					}
        					System.out.println();
        				
        				}
        			}
        		}
        		
//				Can be used to demonstrate DCT works 
//        		m_panelImgOutputDCT.setBufferedImage(m_imgIntermeadiateDCT);
        		

        		for(int y = 0; y < height; y++) {
        			for(int x = 0; x < width; x++) {

        				A = (int) Alpha.data[y][x];
        				R = (int) Math.round(1*Red.data[y][x] + 0*Green.data[y][x] + 1.13983*Blue.data[y][x]);
        				G = (int) Math.round(1*Red.data[y][x] + -0.39465*Green.data[y][x] + -0.58060*Blue.data[y][x]);
        				B = (int) Math.round(1*Red.data[y][x] + 2.03211*Green.data[y][x] + 0*Blue.data[y][x]);
        			
        				P = 0;
        				P = (A<<24) | (R<<16) | (G<<8) | B;
        				
        				m_imgOutput.setRGB(x, y, P);
        				
        				
        			}
        		}
                 
                m_panelImgOutput.setBufferedImage(m_imgOutput);
	        	
	        }
	    	
	    	//saves the jpg file 
	    
	    	else if(evnt.getSource() == m_btSave)
	    	{
	         	if(m_imgOutput == null)
	         		return;
	         	
	         	fc.addChoosableFileFilter(new ImageFilter());
	         	fc.setAcceptAllFileFilterUsed(false);
	         	int returnVal = fc.showSaveDialog(Main.this);
	         	if (returnVal == JFileChooser.APPROVE_OPTION)
	         	{
	         		File file = fc.getSelectedFile();	
	         		try 
	         		{
	             	    ImageIO.write(m_imgOutput, "jpg", file);
	             	} 
	         		catch (IOException e) 
	         		{	 
	         			
	             	}
	         	}
	    	}

		}
	}