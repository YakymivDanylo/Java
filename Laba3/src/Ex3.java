public class Ex3 {
    public static class Box<T>{
        private T item;

        public Box(){};

        public Box(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void put(T item) {
            this.item = item;
        }
    }

    public static class Book{
        String title;

        public Book(String title){this.title = title;}
        @Override
        public String toString(){
            return title;
        }
    }

    public static void  main(String[] args){
        Box<Integer> intBox = new Box<>();
        intBox.put(10);
        System.out.println("String Box: " + intBox.getItem());

        Box<Book> bookBox = new Box<>(new Book("Kobzar"));
        System.out.println("Book Box: " + bookBox.getItem());
    }
}
