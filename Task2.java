1.What is the exact cause of ConcurrentModificationException in Java?
ConcurrentModificationException in Java happens when you change a collection (like a List or Set) while you are looping through it, and the change is not done using the iterator itself.
•You start iterating over a collection 
•While iterating, you add or remove elements directly 
•Java detects this unsafe change and throws ConcurrentModificationException 
This is done to prevent unpredictable or incorrect behavior during iteration.

2.What code pattern at line 142 most likely triggered this error?
--> At line 142, the code was most likely iterating over an ArrayList and modifying the same list during the iteration.
This usually happens when a for-each loop or iterator is used to loop through a list, and elements are added or removed directly from that list inside the loop. Java does not allow this and throws a ConcurrentModificationException.
Example of the problematic pattern:
			for (Transaction t : transactions) {
                                           transactions.remove(t);
}
Java throws this exception to prevent unsafe changes to a list while it is being read.


3.transactions.removeIf(t -> condition);