import java.util.*;

// Transaction class
class Transaction {
    int id;
    String user;
    double amount;
    int fraudScore;

    Transaction(int id, String user, double amount, int fraudScore){
        this.id=id;
        this.user=user;
        this.amount=amount;
        this.fraudScore=fraudScore;
    }

    public String toString(){
        return "ID:"+id+" User:"+user+" Amount:"+amount+" FraudScore:"+fraudScore;
    }
}

// Linked List Node
class Node{
    Transaction data;
    Node next;

    Node(Transaction data){
        this.data=data;
        next=null;
    }
}

// Linked List
class TransactionList{
    Node head;

    void add(Transaction t){
        Node newNode = new Node(t);

        if(head==null){
            head=newNode;
            return;
        }

        Node temp=head;

        while(temp.next!=null)
            temp=temp.next;

        temp.next=newNode;
    }

    void display(){

        Node temp=head;

        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
}

// Queue for processing
class TransactionQueue{

    Queue<Transaction> queue = new LinkedList<>();

    void add(Transaction t){
        queue.add(t);
    }

    void process(){

        if(queue.isEmpty()){
            System.out.println("No transactions to process");
            return;
        }

        Transaction t = queue.remove();

        System.out.println("Processing Transaction: "+t);
    }
}

// Priority Queue (Heap)
class FraudDetector{

    PriorityQueue<Transaction> pq =
            new PriorityQueue<>(
                    (a,b)->b.fraudScore-a.fraudScore
            );

    void add(Transaction t){
        pq.add(t);
    }

    void showHighestFraud(){

        if(pq.isEmpty()){
            System.out.println("No fraud transactions");
            return;
        }

        System.out.println("Highest Fraud Risk Transaction:");
        System.out.println(pq.poll());
    }
}

// Hashing using HashMap
class TransactionHash{

    HashMap<Integer,Transaction> map = new HashMap<>();

    void add(Transaction t){
        map.put(t.id,t);
    }

    void search(int id){

        if(map.containsKey(id))
            System.out.println("Found: "+map.get(id));
        else
            System.out.println("Transaction not found");
    }
}

// Sorting (Quick Sort)
class Sorting{

    static void quickSort(Transaction arr[], int low, int high){

        if(low<high){

            int pi = partition(arr,low,high);

            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }

    static int partition(Transaction arr[], int low, int high){

        double pivot = arr[high].amount;

        int i = low-1;

        for(int j=low;j<high;j++){

            if(arr[j].amount < pivot){

                i++;

                Transaction temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }

        Transaction temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;

        return i+1;
    }
}

// Binary Search
class Searching{

    static int binarySearch(Transaction arr[], int key){

        int low=0;
        int high=arr.length-1;

        while(low<=high){

            int mid=(low+high)/2;

            if(arr[mid].id==key)
                return mid;

            else if(arr[mid].id < key)
                low=mid+1;

            else
                high=mid-1;
        }

        return -1;
    }
}

// Stack Expression Evaluation
class Expression{

    static int evaluate(String exp){

        Stack<Integer> stack = new Stack<>();

        for(char c : exp.toCharArray()){

            if(Character.isDigit(c))
                stack.push(c-'0');

            else{

                int b = stack.pop();
                int a = stack.pop();

                switch(c){

                    case '+': stack.push(a+b); break;
                    case '-': stack.push(a-b); break;
                    case '*': stack.push(a*b); break;
                    case '/': stack.push(a/b); break;
                }
            }
        }

        return stack.pop();
    }
}

// MAIN CLASS
public class FuelFraudSystem {

    public static void main(String args[]){

        // Create transactions
        Transaction t1 = new Transaction(101,"Alice",5000,80);
        Transaction t2 = new Transaction(102,"Bob",2000,20);
        Transaction t3 = new Transaction(103,"Charlie",9000,90);

        // Linked List
        System.out.println("----- Linked List Transactions -----");

        TransactionList list = new TransactionList();

        list.add(t1);
        list.add(t2);
        list.add(t3);

        list.display();


        // Queue
        System.out.println("\n----- Queue Processing -----");

        TransactionQueue queue = new TransactionQueue();

        queue.add(t1);
        queue.add(t2);

        queue.process();


        // Priority Queue
        System.out.println("\n----- Fraud Detection (Priority Queue) -----");

        FraudDetector fd = new FraudDetector();

        fd.add(t1);
        fd.add(t2);
        fd.add(t3);

        fd.showHighestFraud();


        // Hashing
        System.out.println("\n----- HashMap Lookup -----");

        TransactionHash hash = new TransactionHash();

        hash.add(t1);
        hash.add(t2);
        hash.add(t3);

        hash.search(102);


        // Sorting
        System.out.println("\n----- Quick Sort by Amount -----");

        Transaction arr[] = {t1,t2,t3};

        Sorting.quickSort(arr,0,arr.length-1);

        for(Transaction t : arr)
            System.out.println(t);


        // Binary Search
        System.out.println("\n----- Binary Search Transaction -----");

        int index = Searching.binarySearch(arr,102);

        if(index!=-1)
            System.out.println("Transaction Found: "+arr[index]);


        // Stack
        System.out.println("\n----- Stack Expression Evaluation -----");

        System.out.println("Expression 2 3 + = "+Expression.evaluate("23+"));

    }
}