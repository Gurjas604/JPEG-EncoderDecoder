package assignment;

public class QuantizationService {
	
	private double[][] zero(int M,int N){
		
		double[][] X = new double[M][N];
		
		for(int y = 0; y < M; y++) {
			for(int x = 0; N < 3; x++) {
				X[x][y] = 0;
			}
		}
		return X;
	}
	
	
	public double[][] lum(double[][] block){
		if(block.length != 8) {
			System.out.println("Wrong y dimention");
		}
		else if(block[0].length != 8) {
			System.out.println("Wrong x dimention");
		}
		
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
				block[y][x] = Math.round(block[y][x] / table[y][x]);
			}
		}
		
		
		return block;
		
	}
	
	public double[][] unlum(double[][] block){
		if(block.length != 8) {
			System.out.println("Wrong y dimention");
		}
		else if(block[0].length != 8) {
			System.out.println("Wrong x dimention");
		}
		
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
				block[y][x] = (block[y][x] * table[y][x]);
			}
		}
		
		
		return block;
		
	}

	
	public double[][] chrom(double[][] block){
		if(block.length != 8) {
			System.out.println("Wrong y dimention");
		}
		else if(block[0].length != 8) {
			System.out.println("Wrong x dimention");
		}
		
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
				block[y][x] = Math.round(block[y][x] / table[y][x]);
			}
		}
		
		return block;
		
	}
	
	public double[][] unchrom(double[][] block){
		if(block.length != 8) {
			System.out.println("Wrong y dimention");
		}
		else if(block[0].length != 8) {
			System.out.println("Wrong x dimention");
		}
		
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
				block[y][x] = (block[y][x] * table[y][x]);
//				System.out.println("not rounded"+ block[x][y]);
//				System.out.println("rounded"+ Math.round(block[x][y]));
				
			}
		}
		
		return block;
		
	}
	
	public QuantizationService() {
		//does nothing
	}

	public static void main(String[] args) {
		
//		
//		QuantizationService q = new QuantizationService();
//		double[][] test = q.zero(8,8);
//		//initialize new 2DArray for testing
//		
//		for (int y = 0; y < 8; y++) {
//			for (int x = 0; x < 8; x++) {
//				test[y][x] = 1;
//			}
//		}
//		//fill it with numbers
//		
//		test = q.unchrom(test);
//		//test the quantization service
//		
//		for (int y = 0; y < 8; y++) {
//			for (int x = 0; x < 8; x++) {
//				System.out.print(test[y][x] + ", ");
//			}
//				System.out.println();
//		}
//		//print results for conformation
//		
		
	}

}