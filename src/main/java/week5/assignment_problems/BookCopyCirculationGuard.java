public class BookCopyCirculationGuard{
    static class BookInventory{
        private int copiesTotal;
        private int copiesAvailable;
        public BookInventory(int copiesTotal){
            if(copiesTotal<=0)throw new IllegalArgumentException("Copies must be positive");
            this.copiesTotal=copiesTotal;
            copiesAvailable=copiesTotal;
        }
        public void checkOut(){if(copiesAvailable>0)copiesAvailable--;}
        public void checkIn(){if(copiesAvailable<copiesTotal)copiesAvailable++;}
        public int getCopiesAvailable(){return copiesAvailable;}
    }
    public static void main(String[] args){
        BookInventory book=new BookInventory(3);
        book.checkOut();
        System.out.println(book.getCopiesAvailable());
    }
}
