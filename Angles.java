import java.util.*;
 
 
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
 
class Angles {
    
    
    static boolean vals [] = new boolean [150000];
	static TreeSet<Integer> set = new TreeSet<Integer>();
	
	public static void build(int [] data)
	{
		
		int N = data.length;
		
		for(int x: data)
			vals [x] =true;
		
		
		
		for(int i=0;i<150000;i++)
		{
			
			if(!vals[i])
			{
				
				for(int j=0;j<N;j++)
				{
					
					if((i-data[j]) >=0 && vals[i-data[j]])
					{
						vals[i]=true;
						
						set.add(i%360);
						break;
						
						
					}
					
				}
				
			}
			
			
			
		}
		
		
		
		
	}
    
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input
 
        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
 
        //Scanner
        */
        
        Scanner s = new Scanner(System.in);
        
        int N = s.nextInt();
        int K = s.nextInt();
        
        int M [] = new int [N];
        
        for(int i=0;i<N;i++)
         M[i] =s.nextInt();
         
         build(M);
         
         for(int i=0;i<K;i++)
         {
             if(set.contains(s.nextInt()))
             {
                 System.out.println("YES");
                 
             }
             else
             {
                 System.out.println("NO");
                 
             }
             
         }
 
        // Write your code here
 
    }
}