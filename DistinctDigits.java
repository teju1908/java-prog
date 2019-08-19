import java.util.*;
import java.io.*;
class Node {
	int min;
	int max;
	int maxNo;
	int minNo;
}
 
class DistinctDigits {
    public static void main(String args[] ) throws Exception {
      //  Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringBuffer sb = new StringBuffer();
	   
	   int n = Integer.parseInt(br.readLine());
      long mod = (long)1e9+7;
      StringTokenizer stk = new StringTokenizer(br.readLine());
       long[] arr = new long[n];
       for(int i = 0; i<n;i++){
       	arr[i] = Long.parseLong(stk.nextToken());
       }
       
       int height = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
       int max_size = (int) Math.pow(2,height) - 1;
        Node[] seg = new Node[max_size];
      
        seg = createSeg(arr,0,n-1,0, seg);
		
		
		long q = Long.parseLong(br.readLine());
       for(int i  =0; i<q;i++){
       	
       	stk = new StringTokenizer(br.readLine());
       	String type = stk.nextToken();
       	int l = Integer.parseInt(stk.nextToken());
       	long r = Long.parseLong(stk.nextToken());
       	
       	if(type.equals("ADD")){
       		arr[l - 1] = (arr[l - 1]+r)% mod;
       		seg = update(arr,0,n-1,l-1,l-1,0,seg);
       	}
       	
       	else if (type.equals("MUL")){
       		arr[l - 1] = (arr[l - 1]*r)% mod;
       		seg = update(arr,0,n-1,l-1,l-1,0,seg);
       }
       else if(type.equals("REP")){
       	arr[l - 1] = r % mod;
       		seg = update(arr,0,n-1,l-1,l-1,0,seg);
       }
       
       
       else if(type.equals("MAX")){
       	Node tmp = query(seg,0,n-1,l-1,r-1,0);
       		sb.append(tmp.max+" "+tmp.maxNo+"\n");
       }
       
       
       	else if(type.equals("MIN")){
       		Node tmp = query(seg,0,n-1,l-1,r-1,0);
       		sb.append(tmp.min+" "+tmp.minNo+"\n");
       	}
       	
       }
       //System.out.println(getDigits(95897042));
       System.out.println(sb);
    }
    public static Node[] createSeg(long[] arr, int st, int end, int pos, Node[] seg) throws Exception{
		Node temp = new Node();
    	//System.out.println(st+" "+end+" "+pos);
    	if(st == end){
    		temp.max = getDigits(arr[st]);
    		temp.min = temp.max;
    		temp.maxNo = 1;
    		temp.minNo = 1;
    		seg[pos] = temp;
			return seg;
    	}
    	int mid = (st + end)/2 ;
    	seg = createSeg(arr, st, mid, 2*pos+1, seg);
    	seg = createSeg(arr, mid+1, end, 2*pos+2, seg);
		
		Node w1 = seg[2*pos + 1];
    	Node w2 = seg[2*pos + 2];
		
		if(w1.max == w2.max){
			temp.max = w1.max;
			temp.maxNo = w1.maxNo + w2.maxNo;
		}else {
			temp.max = Math.max(w1.max,w2.max);
			temp.maxNo = w1.max > w2.max ? w1.maxNo : w2.maxNo;
		}
		if(w1.min == w2.min){
			temp.min = w1.min;
			temp.minNo = w1.minNo +  w2.minNo;
		}else {
			temp.min = w1.min < w2.min ? w1.min : w2.min;
			temp.minNo = w1.min < w2.min ? w1.minNo : w2.minNo;
		}
		seg[pos] = temp;
		return seg;
    }
    public static Node query(Node[] seg, int st, int end, int l, long r,int pos){
    	if(l >end || r < st){
    		return null;
    	}
    	if(l <= st && r >= end){
    		return seg[pos];
    	}
    	else {
    		Node temp = new Node();
    		int mid = (st+end)/2;
    		Node w1 = query(seg, st, mid, l, r, 2*pos+1);
    		Node w2 = query(seg, mid+1, end, l, r, 2*pos+2);
    		if(w1 == null){
    			return w2;
    		}
    		if(w2 == null){
    			return w1;
    		}
    		if(w1.max == w2.max){
			temp.max = w1.max;
			temp.maxNo = w1.maxNo + w2.maxNo;
		}else {
			temp.max = Math.max(w1.max,w2.max);
			temp.maxNo = w1.max > w2.max ? w1.maxNo : w2.maxNo;
		}
		if(w1.min == w2.min){
			temp.min = w1.min;
			temp.minNo = w1.minNo +  w2.minNo;
		}else {
			temp.min = w1.min < w2.min ? w1.min : w2.min;
			temp.minNo = w1.min < w2.min ? w1.minNo : w2.minNo;
		}
		return temp;
    	}
    }
    public static Node[] update(long[] arr, int st, int end, int l, int r, int pos, Node[] seg)
    {
    	if(st > r || end < l ){
    		return seg;
    	}
    	Node temp = new Node();
    	if(l == st && r == end){
    		temp.max = getDigits(arr[st]);
    		temp.min = temp.max;
    		temp.maxNo = 1;
    		temp.minNo = 1;
			seg[pos] = temp;
			return seg;
    	}
    	int mid = (st + end)/2;
    	seg = update(arr, st, mid,l,r, 2*pos+1, seg);
    	seg = update(arr, mid+1, end,l,r, 2*pos+2, seg);
		
		Node w1 = seg[2*pos + 1];
    	Node w2 = seg[2*pos + 2];
		
		if(w1.max == w2.max){
			temp.max = w1.max;
			temp.maxNo = w1.maxNo + w2.maxNo;
		}else {
			temp.max = Math.max(w1.max,w2.max);
			temp.maxNo = w1.max > w2.max ? w1.maxNo : w2.maxNo;
		}
		if(w1.min == w2.min){
			temp.min = w1.min;
			temp.minNo = w1.minNo +  w2.minNo;
		}else {
			temp.min = w1.min < w2.min ? w1.min : w2.min;
			temp.minNo = w1.min < w2.min ? w1.minNo : w2.minNo;
		}
		seg[pos] = temp;
		return seg;
    	
    }
    public static int getDigits(long value){
    	boolean[] arr = new boolean[10];
    	int count = 0;
    	while(value > 0){
    		int tp = (int)value % 10;
    		value = value / 10;
    		if(!arr[tp]){
    			arr[tp] = true;
    			count++;
    		}
    	}
    	return count;
    }
}