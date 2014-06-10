package util;

import java.util.Calendar;

import logic.logicInterface.EventService;
import logic.logicInterface.UserService;

public class RoundTimer {
	private Thread roundTimerThread;
	private UserService userService;
	private EventService eventService;
	
	private Calendar signCalendar;
	
	public RoundTimer(){
		signCalendar = getFreshCalendar();
		
		roundTimerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					//settleUsers();
					
					//settleEvents();
					
					try {
						//每分钟执行一次
						Thread.sleep(1000*60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		roundTimerThread.start();
	}

	
	/**
	 * 结算用户时间
	 */
	private void settleUsers() {
		Calendar cal = Calendar.getInstance();
		
		//检查当前时间和上一次的标记时间是否有较大的时间差
		boolean hasNewDay = hasNewDay(cal, signCalendar);

		if (userService != null && hasNewDay) {
			signCalendar = getFreshCalendar();
			userService.settleUserState();
		}

	}
	
	/**
	 * 结算活动状态（是否应该关闭）
	 */
	private void settleEvents() {
		System.out.println("settling events");
		
		if ( eventService != null ){
			//eventService.settleEventState();
		}
	}

	/**
	 * 判断两个时间之间是否差距超过一天（意味着要开始结算了）
	 * @param lcal
	 * @param rcal
	 * @return
	 */
	private boolean hasNewDay(Calendar lcal, Calendar rcal) {
		long ltime = lcal.getTimeInMillis();
		long rtime = rcal.getTimeInMillis();
		
		long diff = ltime - rtime;
		if( diff < 0 ){
			diff = -diff;
		}
		
		if( diff > 24*60*60*1000 ){
			return true;
		}

		
		return false;
	}

	/**
	 * 获得当天的日期（时分秒都设定为0的版本）
	 * @return
	 */
	private Calendar getFreshCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal;
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
}
