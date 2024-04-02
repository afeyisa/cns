class Main{
  private static String algorithm ="3DES";
  private static String message ="Enter the message before";

  public static void setMessage(String s){
      message=s;
  }
  public static String getMessage(){
      return message;
  }
  public static void setA(String s){
      algorithm=s;
  }
  public static String getAlgorithm(){
      return algorithm;
  }


    public static void main(String[] arge){

        new GUI();
    }
}