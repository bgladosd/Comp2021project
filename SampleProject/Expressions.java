public class Expressions {

    String expName;
    String expRef1;
    String bop;
    String expRef2;

    int exp1;
    int exp2;
    int howToCal=0;//0= both var, 1 = 1 is var ,2 = 2is var, 3= both num

    public Expressions(String a,String b,String c,String d){
        this.expName=a;
        this.expRef1=b;
        this.bop=c;
        this.expRef2=d;

        Boolean checkNum1=true;
        Boolean checkNum2=true;

        for(int i=0;i<=expRef1.length()-1;i++){
            if(!((expRef1.charAt(i)-48)>=0 && (expRef1.charAt(i)-48)<10)){
                checkNum1=false;
            }
        }
        if(checkNum1){
            exp1=0;
            int temp=1;
            for(int j=0;j<=expRef1.length()-2;j++)temp*=10;
            for(int i=0;i<=expRef1.length()-1;i++){
                exp1+=(temp*(expRef1.charAt(i)-48));
                temp/=10;
            }
        }

        for(int i=0;i<=expRef2.length()-1;i++){
            if(!((expRef2.charAt(i)-48)>=0 && (expRef2.charAt(i)-48)<10)){
                checkNum2=false;
            }
        }

        if(checkNum2){
            exp2=0;
            int temp=1;
            for(int j=0;j<=expRef2.length()-2;j++)temp*=10;
            for(int i=0;i<=expRef2.length()-1;i++){
                exp2+=(temp*(expRef2.charAt(i)-48));
                temp/=10;
            }
        }


        if(checkNum1==false&&checkNum2==false){
            howToCal=0;
        }
        else if(checkNum1==false&&checkNum2==true){
            howToCal=1;
        }
        else if(checkNum1==true&&checkNum2==false){
            howToCal=2;
        }
        else howToCal=3;

    }


}
