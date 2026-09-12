public class LibraryMemberJavaBean{
    static class LibraryMember{
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswerHash;
        public LibraryMember(){this(null,null);}
        public LibraryMember(String name){this(null,name);}
        public LibraryMember(String membershipId,String name){
            this.membershipId=membershipId;
            this.name=name;
        }
        public String getMembershipId(){return membershipId;}
        public void setMembershipId(String membershipId){if(this.membershipId==null&&membershipId!=null&&!membershipId.trim().isEmpty())this.membershipId=membershipId;}
        public String getName(){return name;}
        public void setName(String name){this.name=name;}
        public boolean isPremiumMember(){return premiumMember;}
        public void setPremiumMember(boolean premiumMember){this.premiumMember=premiumMember;}
        public void setSecurityAnswer(String securityAnswer){if(securityAnswer!=null)securityAnswerHash=Integer.toHexString(securityAnswer.hashCode());}
    }
    public static void main(String[] args){
        LibraryMember member=new LibraryMember();
        member.setMembershipId("LIB-8841");
        System.out.println(member.getMembershipId());
    }
}
