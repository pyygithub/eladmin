package me.zhengjie.modules.monitor.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import me.zhengjie.modules.monitor.domain.LogMessage;

import java.text.DateFormat;
import java.util.Date;

/**
 * 定义Logfilter拦截输出日志
 * @author pyy
 * @date 2019-05-27 09:55:57
 */
public class LogFilter extends Filter<ILoggingEvent>{

    @Override
    public FilterReply decide(ILoggingEvent event) {
        LogMessage loggerMessage = new LogMessage(
                event.getFormattedMessage(),
                DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr
        );
        LoggerQueue.getInstance().push(loggerMessage);
        return FilterReply.ACCEPT;
    }
}