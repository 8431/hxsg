package com.hxsg.systemdao;

/**
 * Created by dlf on 2016/10/13.
 * 推送系统聊天中的世界消息---
 * 区------派------商------【世】
 * 13:21 系统：本期猜猜猜 开112，小。玩家共赢的300金10000银
 * 13:21 系统：本期猜猜猜 开112，小。玩家共赢的300金10000银
 * 13:21 系统：本期猜猜猜 开112，小。玩家共赢的300金10000银
 * 13:21 系统：本期猜猜猜 开112，小。玩家共赢的300金10000银
 */
public interface SystemNotification {
    /**
     * 将最世界最新消息保存到数据库中，并推送到页面
     * @param msg
     * @return 结果状态  true  false
     */
    public String sendChatToWorld(String msg)throws Exception;

    /**
     * 推送系统消息
     * @param msg
     */
    public void sendSystemMsg(Object msg)throws Exception;
    /**
     * 推送系统消息给指定用户
     * @param msg
     */
    public void sendSystemMsg(Object msg, String roleId);
}
