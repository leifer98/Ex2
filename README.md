# Ex2_1

In order to run the project, one should clone the repository to its' computer or download a zip of it, then open it
through an IDE, go to the Ex2_1.Ex2_1.java file inside /src folder and run it.

In this assignment we were asked to implement a research on usage of threads.
In Java, a Thread is a concurrent unit of execution. It has its own call stack for methods being invoked,
their arguments and local variables. Each Java application has at least one thread, the main thread,
which is created by the JVM (Java Virtual Machine) when the application starts.
A ThreadPool is a collection of worker threads that are waiting to be dispatched to execute tasks.
When you create a new task, you submit it to the thread pool, which assigns it to one of the available worker threads.
This can be more efficient than creating a new thread for every task, because creating and starting a new thread can be
expensive in terms of time and resources.

One of the ways to measure efficiency is by the time of multiple processes. In the assignment we used the concurrent
library that allows us to do so. below there is a explanation about how measured the time.

Ex2_1.FileHandling.java :
This code defines a Ex2_1.FileHandling class with two static methods: writeFile and getFileNumOfLines.
The writeFile method takes a fileName and an x as arguments and writes "happy hanuka!"
to the file specified by fileName x number of times. It does this by creating a FileWriter and a PrintWriter,
and then using a loop to write the string to the file x times. Finally, it closes the PrintWriter and the FileWriter.
The getFileNumOfLines method takes a fileName as an argument and returns the number of lines in the file specified by
fileName. It does this by creating a FileReader and a BufferedReader, and then using a loop to read each line of the
file until the end is reached. It counts the number of lines as it reads them and returns the count when the end of
the file is reached. Finally, it closes the BufferedReader and the FileReader.
There is also an optional block of code that causes the thread to sleep for 100 milliseconds after reading the file.
This will be used to simulate some delay.

Ex2_1.FileReaderCallable.java :
This code defines a Ex2_1.FileReaderCallable class that implements the Callable interface.
Callable is similar to Runnable, but it can return a value and throw checked exceptions.
The Ex2_1.FileReaderCallable class has a single field, fileName, which is the name of the file to be read. It has a single method,
call, which returns the number of lines in the file specified by fileName. It does this by calling the getFileNumOfLines
method of the Ex2_1.FileHandling class, passing it fileName as an argument.
The call method is annotated with @Override, which means that it is intended to override a method with the same
signature in a superclass or interface. In this case, call is the implementation of the call method of the Callable interface.
You can use the Ex2_1.FileReaderCallable class to create a FutureTask that represents the asynchronous computation of the
number of lines in a file. You can then submit the FutureTask to an Executor to have it executed by a worker thread.
When the computation is complete, you can retrieve the result using the get method of the FutureTask.

Ex2_1.FileReaderThread.java :
This code defines a Ex2_1.FileReaderThread class that extends the Thread class. Ex2_1.FileReaderThread has two fields:
fileName, which is the name of the file to be read, and numOfLines, which is the number of lines in the file.
The Ex2_1.FileReaderThread class has a constructor that takes a name and a fileName as arguments and passes the name
to the constructor of the Thread superclass. It also initializes the fileName field with the fileName argument.
The Ex2_1.FileReaderThread class overrides the run method of the Thread class. The run method calls the getFileNumOfLines
method of the Ex2_1.FileHandling class, passing it fileName as an argument, and stores the result in the numOfLines field.
The Ex2_1.FileReaderThread class also has a getNumOfLines method that returns the value of the numOfLines field.
You can use the Ex2_1.FileReaderThread class to create a new thread that reads the number of lines in a file and stores
the result. To do this, you would create a new Ex2_1.FileReaderThread instance, passing it the name of the thread and the
name of the file as arguments, and then call the start method to start the thread. The numOfLines field will be
populated with the result when the thread finishes executing.

Ex2_1.Ex2_1.java :
This code appears to be reading in a list of files,
and then using three different approaches to count the
number of lines in those files: getNumOfLines, getNumOfLinesThreads, and getNumOfLinesThreadPool.
getNumOfLines simply iterates through the list of file names, and for each file, it calls
Ex2_1.FileHandling.getFileNumOfLines to count the number of lines in the file.
getNumOfLinesThreads creates a separate Ex2_1.FileReaderThread for each file,
starts all the threads, and then waits for them all to finish.
Each Ex2_1.FileReaderThread is responsible for counting the number of lines in a single file.
getNumOfLinesThreadPool uses a thread pool to execute a Ex2_1.FileReaderCallable
for each file concurrently. Each Ex2_1.FileReaderCallable is responsible for counting the number of lines in a single file.
Finally, the main method measures the execution time of each of these three approaches and prints the results which tell us
which option is efficient on time.

we ran 2 tests, one with the delay and one without.

with delay :
output -
Elapsed time for getNumOfLines(): 54257 milliseconds
Elapsed time for getNumOfLinesThreads(): 151 milliseconds
Elapsed time for getNumOfLinesThreadPool(): 167 milliseconds

Based on the output we got, it looks like getNumOfLinesThreads and getNumOfLinesThreadPool both execute significantly
faster than getNumOfLines.
This is because getNumOfLinesThreads and getNumOfLinesThreadPool are able to count the number of lines in multiple files
concurrently, while getNumOfLines counts the number of lines in each file sequentially. This allows getNumOfLinesThreads
and getNumOfLinesThreadPool to take advantage of multiple CPU cores and complete the task faster.
getNumOfLinesThreads appears to be slightly faster than getNumOfLinesThreadPool. This may be because
getNumOfLinesThreads does not have the overhead of submitting tasks to an executor service and returning futures,
as getNumOfLinesThreadPool does.
picture:

without delay :
output -
Elapsed time for getNumOfLines(): 67 milliseconds
Elapsed time for getNumOfLinesThreads(): 83 milliseconds
Elapsed time for getNumOfLinesThreadPool(): 83 milliseconds

Based on the revised output we got, it looks like getNumOfLinesThreads and getNumOfLinesThreadPool now have
similar execution times to getNumOfLines.
This is likely because the execution time of getFileNumOfLines has been reduced,
that happened by removing the commented-out code that caused it to sleep for 100 milliseconds.
As a result, the benefits of concurrent execution offered by getNumOfLinesThreads and getNumOfLinesThreadPool are
not as significant as before.
It's worth noting that the execution times of all three methods may vary from run to run due to differences in system
load and other factors. If you want to accurately compare the execution times of these methods.


In general, it is often beneficial to use concurrency when counting the number of lines in multiple files, as it allows
you to take advantage of multiple CPU cores and complete the task faster.

However, the specific approach you choose will depend on your specific requirements and the characteristics of your
workload. For example, if you have a very large number of files to process and the time taken to count the lines in each
file is relatively small, a thread pool may be the most efficient approach, as it allows you to process multiple files
concurrently while minimizing the overhead of creating and managing threads. On the other hand, if you have a smaller
number of files to process and the time taken to count the lines in each file is relatively large, using a separate
thread for each file may be more efficient, as it allows you to fully utilize the available CPU resources.

It's also worth noting that there are other approaches to counting the number of lines in multiple files concurrently,
such as using the ForkJoinPool class or the CompletableFuture class. These approaches may offer additional flexibility
and scalability, depending on your specific needs.

Class Diagram:

![Screenshot (92)](https://user-images.githubusercontent.com/24610228/211369237-13d439cf-4067-4584-8c86-06f3d761b582.png)

# Ex2_2

In order to run the project, one should clone the repository to its' computer or download a zip of it, then open it through an IDE, go to the src/Ex2_2/Tests and run it.

In this assignment we were asked to create two new types that extend the functionality of Java's Concurrency Framework.

A generic task with a Type that returns a result and may throw an exception. Each task has a priority used for scheduling inferred from the integer value of the task's Type.
A custom thread pool class that defines a method for submitting a generic task as described in the section 1 to a priority queue, and a method for submitting a generic task created by a Callable and a Type, passed as arguments. A ThreadPool is a collection of worker threads that are waiting to be dispatched to execute tasks. When you create a new task, you submit it to the thread pool, which assigns it to one of the available worker threads. This can be more efficient than creating a new thread for every task, because creating and starting a new thread can be expensive in terms of time and resources.
Our implementation of Task starts with implementing Callable. Thus, if one would try Task.start(), the callable thread of the task would run. The task class is generic and creates instances with factory method (that uses the class's private constructors). The task has a TaskType and a callable objects, as required, and FutureTask and CustomExecutor object (the FutureTask object holds the callable, and we can get the generic value that returns from the callable from it. The Task holds its executor so that if we change the Task's priority, we would be able to notify the executor to make the necessary changes). When creating task instances, we used a Factory method. The factory design pattern says that define an interface ( A java interface or an abstract class) for creating object and let the subclasses decide which class to instantiate. The factory method in the interface lets a class defers the instantiation to one or more concrete subclasses (From GFG)


The Adapter design pattern is a structural design pattern that allows objects with incompatible interfaces to work together.
It does this by creating a wrapper class that converts the interface of one class into the interface expected by the client.
There are two types of adapter patterns:
Object Adapter: Which use composition and a wrapper class to adapt one interface to another.
Class Adapter: Which multiple inheritance to adapt one interface to another.
The Adapter pattern can be very useful when dealing with code that is built on top of third-party libraries, 
or when trying to reuse existing code in a new context.
the Task class serves as an Adapter between the Callable interface and the TaskType and FutureTask classes, 
adapting the Callable interface so that it can be used with the TaskType and FutureTask classes. Additionally, 
it also provides additional functionality by allowing tasks to be classified into types and be assigned with priority.

Our implementation of CustomExecutor starts with extending Thread. We're doing it so the CustomExecutor will have a daemon thread ( daemon thread is a thread that does not prevent the JVM from exiting when the program finishes but the thread is still running) that runs in the background and does all the stuff we were asked to do again and again in the assignment (such as getting rid of unused threads, inserting new ones from the queue, updating the currentMaxPriority ect). In Order to save the tasks, sorted by priority - we used a PriorityQueue that gets as a parameter a comparator that we implemented (the PriorityComparator class). Moreover, we made sure that as we were asked in the instructions - to lock the access to the queue every time it is used, by using an object named lock and synchronizing it.

The test checks functionality and execution of tasks with different priorities using the CustomExecutor, Tasks with callables that returns different values, and checks the getMaxPriority function in O(1) time.

The difficulties we have encountered during the assignment were mostly understanding what neede to be done (the instructions were vauge, so we'll think for ourselves about how to implement what we were asked). Moreover, the getCurrentMax function was a bit challenging, because this field had to be updated at all time (following the instructions, we weren't allowed to access the queue when activating this function). We overcame these difficulties by researching on ThreadPool executor and its functionality, and by using daemon thread that updates the maximum priority at any given time if necessary (explanation on daemon thread exists above).

The proposed design contributed to enhance the flexibility, performance, and maintainability of our code because the daemon thread is always running and any desired functionality can be added without disturbing or imparing it.

Class Diagram: 

![211212791-5b9cf8ec-15cd-4a08-9beb-d5414814cb61](https://user-images.githubusercontent.com/24610228/211369452-970b9966-5a3a-468e-acf9-b5b513b8b00b.jpg)

