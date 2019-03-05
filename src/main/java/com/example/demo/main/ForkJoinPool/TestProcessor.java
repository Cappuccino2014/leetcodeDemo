package com.example.demo.main.ForkJoinPool;

/**
 * 测试样例
 * 
 * Title:TestProcessor
 * Company:Mobgeek
 * @author Joe
 * @date 2018年8月2日 下午4:23:30
 */
public class TestProcessor extends TaskProcessor<TestJson> {
	
	private String url;
	
	public TestProcessor(String url){
		this.url = url;
	}

	@Override
	public TestJson process() throws Exception {
		Thread.sleep(1000);
		TestJson testJson = new TestJson();
		testJson.setName(this.getProcessorId());
		testJson.setUrl(url);
		return testJson;
	}

}
