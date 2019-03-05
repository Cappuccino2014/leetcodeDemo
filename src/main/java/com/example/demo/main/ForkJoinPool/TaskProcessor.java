package com.example.demo.main.ForkJoinPool;

import java.util.UUID;

/**
 * 最小的任务处理单元
 * Title:Processor
 * Company:Mobgeek
 * @author Joe
 * @date 2018年8月1日 下午8:01:50
 */
public abstract class TaskProcessor<V>{
	
	private String processorId;
	
	private boolean isSyn = false;  //是否并行  true串行任务 false：并行任务
	
	private V result;   //任务返回的结果
	
	public TaskProcessor(){
		processorId = UUID.randomUUID().toString();
	}
	

	/**
	 * 处理任务逻辑
	 */
	public abstract V process() throws Exception;
	
	public void setSyn(boolean isSyn) {
		this.isSyn = isSyn;
	}
	
	public boolean getIsSyn(){
		return isSyn;
	}
	
	public String getProcessorId() {
		return processorId;
	}
	
	public void setResult(V result) {
		this.result = result;
	}
	
	public V getResult() {
		return result;
	}

}
