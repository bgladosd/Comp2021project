public class main {

    private static int varIndex = 0;
    private static int expIndex = 0;
    private static int progIndex =0;

    public static Variable[] varList = new Variable[100];
    public static Expressions[] expList = new Expressions[100];
    public static Program[] progList = new Program[100];




    static public void vardef(String lab,String varName,String typ,String expRef){
        int varType=0; //0 = error 1 = int / boolean
        if(typ.equals("int") || typ.equals("bool"))varType=1;

        if(varType==0){
            System.out.println("error");
        }

        else {
            Variable temp = new Variable(lab,typ,varName,expRef);
            varList[varIndex++]=temp;
        }

    }

    static public void binexpr(String expName,String expRef1,String bop,String expRef2){
        Expressions temp = new Expressions(expName,expRef1,bop,expRef2);
        expList[expIndex++]=temp;
    }

    static public void program(String name,String statementLab){
        int found=-1;
        Program temp=null;

        for(int i=0;i<=expIndex-1;i++){
            if(statementLab.equals(expList[i].expName)){
                temp=new Program(name,1,statementLab);
                found=1;
                break;
            }
        }


        if(found==-1)System.out.println("Label not found");
        else progList[progIndex++]=temp;

    }

    static public void translate(String command){
        String[] spliter=command.split(" ");

        //req 1
        if(spliter[0].equals("vardef")){
            vardef(spliter[1],spliter[2],spliter[3],spliter[4]);
        }

        //req 2
        else if(spliter[0].equals("binexpr")){
            binexpr(spliter[1],spliter[2],spliter[3],spliter[4]);
        }

        //req 10
        else if(spliter[0].equals("program")){
            program(spliter[1],spliter[2]);
        }


    }










    static public void execute(String prog){
        int found=-1;
        int pos=-1;
        for(int i=0;i<=progIndex-1;i++){
            if(prog.equals(progList[i].name)){
                found=progList[i].progTyp;
                pos=i;
                break;
            }
        }

        if(found==-1){
            System.out.println("Program not found");
            return;
        }

        if(found==1){
            for(int i=0;i<=expIndex-1;i++){
                if(progList[pos].lab.equals(expList[i].expName)){
                    int num1=0,num2=0,ans;




                    if(expList[i].howToCal==0){

                        for(int j=0;j<=varIndex-1;i++){
                            if(expList[pos].expRef1.equals(varList[j].name)){
                                num1=varList[j].intValue;
                                break;
                            }
                        }

                        for(int j=0;j<=varIndex-1;j++){
                            if(expList[pos].expRef2.equals(varList[j].name)){
                                num2=varList[j].intValue;
                                break;
                            }
                        }
                    }


                    else if(expList[i].howToCal==1){

                        for(int j=0;j<=varIndex-1;i++){
                            if(expList[pos].expRef1.equals(varList[j].name)){
                                num1=varList[j].intValue;
                                break;
                            }
                        }

                        num2=expList[pos].exp2;
                    }

                    else if(expList[i].howToCal==2){

                        for(int j=0;j<=varIndex-1;i++){
                            if(expList[pos].expRef2.equals(varList[j].name)){
                                num2=varList[j].intValue;
                                break;
                            }
                        }

                        num1=expList[pos].exp1;
                    }

                    else {
                        num1=expList[pos].exp1;
                        num2=expList[pos].exp2;
                    }


                    if(expList[pos].bop.equals("*")){
                        ans=num1*num2;
                        System.out.println(ans);
                    }





                    break;
                }
            }
        }





    }








    static public void main(String[] arg){

        String command1="vardef vardef1 x int 100";
        String command2="vardef vardef2 y int 5";
        translate(command1);
        translate(command2);
        System.out.println(varList[0].intValue);
        System.out.println(varList[1].intValue);
        String command3="binexpr exp1 x * y";
        String command5="binexpr exp2 x * 20";
        translate(command3);
        System.out.println(expIndex);
        String command4="program program1 exp1";
        translate(command4);
        execute("program1");



        translate(command5);
        System.out.println(expIndex);
        String command6="program program2 exp2";
        translate(command6);
        execute("program2");
    }
}


//-------------------------------------------------------
