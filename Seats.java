import java.util.Scanner;
import java.lang.Math;
 
 
class Seats {
    public static void OppSeat(int R) {
        String seat;
        if (R % 6 == 0 || (R - 1) % 6 == 0) {
            seat = "WS";
            }
        else if(R%3==0 || (R-1)%3==0){
            seat="AS";
        }
        else {
            seat = "MS";
        }
        double factor=Math.ceil(R/12.0);
        double opp= (Math.ceil((R/12.0)-1)*12.0)+(((factor*12)-R)+1);
        System.out.println(Math.round(opp)+" "+seat);
    }
 
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        int a[]=new int[N];
        for (int i = 0; i < N ; i++) {
            a[i]=sc.nextInt();
        }
        for (int j = 0; j < N ; j++) {
            OppSeat(a[j]);
        }
 
 
    }
}