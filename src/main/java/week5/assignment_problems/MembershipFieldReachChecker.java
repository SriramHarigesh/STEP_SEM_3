public class MembershipFieldReachChecker{
    public static String classifyAccess(String fieldModifier,String accessorContext){
        if(fieldModifier.equals("public"))return "ALLOWED";
        if(fieldModifier.equals("private"))return accessorContext.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        return accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
    }
    public static String summarizeByModifier(String[][] attempts){
        String[] modifiers={"private","default","protected","public"};
        StringBuilder result=new StringBuilder();
        for(int i=0;i<modifiers.length;i++){
            int allowed=0,denied=0;
            for(String[] attempt:attempts){
                if(attempt[0].equals(modifiers[i])){
                    if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))allowed++;
                    else denied++;
                }
            }
            if(i>0)result.append(" | ");
            result.append(modifiers[i]).append(": ").append(allowed).append(" allowed / ").append(denied).append(" denied");
        }
        return result.toString();
    }
    static class LibraryMember{
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;
        public LibraryMember(String membershipId,String branchCode,double finesOwed,String displayName){
            if(membershipId==null||membershipId.trim().length()<4)throw new IllegalArgumentException("Invalid membership ID");
            this.membershipId=membershipId.trim();
            this.branchCode=branchCode;
            this.finesOwed=finesOwed;
            this.displayName=displayName;
        }
    }
    public static void main(String[] args){
        System.out.println(classifyAccess("private","SAME_CLASS"));
    }
}
