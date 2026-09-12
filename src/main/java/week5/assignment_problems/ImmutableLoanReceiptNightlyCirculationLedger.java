public class ImmutableLoanReceiptNightlyCirculationLedger{
    static class LoanReceipt{
        private final String memberId;
        private final String[] bookIds;
        static String ledgerStatus;
        static{ledgerStatus="Nightly circulation ready";}
        public LoanReceipt(String memberId,String[] bookIds){
            if(memberId==null||memberId.trim().isEmpty()||bookIds==null)throw new IllegalArgumentException("Invalid receipt");
            for(String id:bookIds)if(id==null||!id.matches("BK-\\d{3}"))throw new IllegalArgumentException("Invalid book ID");
            this.memberId=memberId;
            this.bookIds=bookIds.clone();
        }
        public String getMemberId(){return memberId;}
        public String[] getBookIds(){return bookIds.clone();}
        public LoanReceipt withCorrectedBookId(int index,String newId){
            String[] corrected=getBookIds();
            corrected[index]=newId;
            return new LoanReceipt(memberId,corrected);
        }
    }
    static class ReferenceOnlyLoanReceipt extends LoanReceipt{
        private final String roomNumber;
        public ReferenceOnlyLoanReceipt(String memberId,String[] bookIds,String roomNumber){
            super(memberId,bookIds);
            this.roomNumber=roomNumber;
        }
        public String getRoomNumber(){return roomNumber;}
    }
    public static String processNightlyCirculation(LoanReceipt[] receipts){
        int processed=0,nullSkipped=0,referenceOnly=0,regular=0;
        if(receipts==null)return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        for(LoanReceipt receipt:receipts){
            if(receipt==null){nullSkipped++;continue;}
            processed++;
            if(receipt instanceof ReferenceOnlyLoanReceipt)referenceOnly++;
            else regular++;
        }
        return processed+" processed | "+nullSkipped+" null skipped | "+referenceOnly+" reference-only | "+regular+" regular";
    }
    public static void main(String[] args){
        System.out.println(processNightlyCirculation(new LoanReceipt[]{new LoanReceipt("LIB-001",new String[]{"BK-100"})}));
    }
}
