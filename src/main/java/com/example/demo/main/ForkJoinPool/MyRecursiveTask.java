package com.example.demo.main.ForkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask<V> extends RecursiveTask<List<TaskProcessor<V>>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2438620976264776064L;

	private int threshold = 16;
	
	private int parallelism = 8;

	private List<TaskProcessor<V>> taskList;

	public MyRecursiveTask(List<TaskProcessor<V>> taskList) {
		this.taskList = taskList;
	}
	
	public MyRecursiveTask<V> threshold(int threshold){
		this.setThreshold(threshold);
		return this;
	}
	
	public MyRecursiveTask<V> parallelism(int parallelism){
		this.setParallelism(threshold);
		return this;
	}
	
	/**
	 * 开始任务
	 */
	public List<V> callback(){
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
		
		List<TaskProcessor<V>> resul  = forkJoinPool.invoke(new MyRecursiveTask<V>(this.getTaskList()));
		
		List<V> valueList = new ArrayList<V>();
		
		for(TaskProcessor<V> taskProcessor : resul){
			valueList.add(taskProcessor.getResult());
		}
		return valueList;
	}

	@Override
	protected List<TaskProcessor<V>> compute() {

		if (taskList.size() > threshold) {
			/**
			 * 对任务进行拆分
			 */
			List<MyRecursiveTask<V>> subTasks = this.createSubtasks();

			for (MyRecursiveTask<V> subTask : subTasks) {
				subTask.fork();
			}
			
			List<TaskProcessor<V>> list = new ArrayList<TaskProcessor<V>>();
            for(MyRecursiveTask<V> subtask : subTasks) {
            	list.addAll(subtask.join());
            }
            return list;
		} else {
			
			final List<TaskProcessor<V>> result = new ArrayList<TaskProcessor<V>>();
			for(TaskProcessor<V> processor : taskList){
				
				V v = null;
				try {
					v = processor.process();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
				if(v == null){
					continue;
				}
				
				processor.setResult(v);
				
				result.add(processor);
				System.out.println(processor.getProcessorId() + " end task :" +Thread.currentThread().getName()+":"+(System.currentTimeMillis() / 1000));
			}
			return result;
		}
		
	}

	private List<MyRecursiveTask<V>> createSubtasks() {
		List<MyRecursiveTask<V>> subtasks = new ArrayList<MyRecursiveTask<V>>();

		int lenth = taskList.size();

		List<TaskProcessor<V>> sub1 = taskList.subList(0, lenth / 2);
		List<TaskProcessor<V>> sub2 = taskList.subList(lenth / 2, lenth);

		MyRecursiveTask<V> subtask1 = new MyRecursiveTask<V>(sub1);
		MyRecursiveTask<V> subtask2 = new MyRecursiveTask<V>(sub2);

		subtasks.add(subtask1);
		subtasks.add(subtask2);

		return subtasks;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}
	
	public List<TaskProcessor<V>> getTaskList() {
		return taskList;
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	public static void main(String[] args) {
		List<TestProcessor> list = new ArrayList<TestProcessor>();
		
		for(int i = 0 ; i < 100 ;i++){
			TestProcessor testProcessor = new TestProcessor("http://test/"+i);
			list.add(testProcessor);
		}
		
		long start = System.currentTimeMillis();
		MyRecursiveTask<TestJson> task = new MyRecursiveTask(list);
		
		List<TestJson> resulTestJsons =  task.callback();

		
		
		for(TestJson testJson : resulTestJsons){
			System.out.println(">>>>"+testJson.toString());
		}
		
		System.out.println("end :"+(System.currentTimeMillis()-start));

	}

}
