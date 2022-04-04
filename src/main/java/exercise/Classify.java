package exercise;

import java.util.HashMap;
import java.util.Map;

public class Classify {

        public Map<Integer,String> checkingASet(int start, int end){
            Map<Integer,String> resMap = new HashMap<>();
            for(int i = start; i<=end; i++){
                resMap.put(i,oddOrEvenOrPrime(i));
            }
            return resMap;
        }


        public String oddOrEvenOrPrime(int number){
            String res;
//            int div = number/2;

            if(isPrime(number)){
                res = "prime";
            }
            else {
                if (number % 2 == 0) {
                    res = "even";
                } else {
                    res = "odd";
                }
            }
            return res;
        }

        public boolean isPrime(int number){
            int i,m,flag=0;
            boolean res = false;
            m=number/2;
            if(number==0||number==1){
               res = false;
            }else{
                for(i=2;i<=m;i++){
                    if(number%i==0){
//                        res=false;
                        flag=1;
                        break;
                    }
                }
                if(flag==0)  {
                    res=true;
                }
            }
             return res;
        }  

}
