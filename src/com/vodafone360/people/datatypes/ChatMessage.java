/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at
 * src/com/vodafone360/people/VODAFONE.LICENSE.txt or
 * http://github.com/360/360-Engine-for-Android
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at src/com/vodafone360/people/VODAFONE.LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 * Copyright 2010 Vodafone Sales & Services Ltd.  All rights reserved.
 * Use is subject to license terms.
 */

package com.vodafone360.people.datatypes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.vodafone360.people.service.io.rpg.RpgPushMessage;
import com.vodafone360.people.utils.LogUtils;

public class ChatMessage extends BaseDataType {
    /** Unique server-supplied conversation id */
    private String mConversationId;

    /** The "from" user */
    private String mUserId;

    /** localContactId of the "from" user in the Contacts table */
    private long mLocalContactId;

    /** Network id of the "from" user (as in the SocialNetwork enumeration) */
    private int mNetworkId;

    private String mBody;

    /** The "to" user */
    private List<String> mTos;
    
    /**
     * When the message was created (for sent messages) /received by the client.
     */
    private long mTimeStamp;

    public enum Tags {
        CONVERSATION_ID("conversation"),
        RECIPIENTS_LIST("tos"),
        FROM("from"),
        BODY("body");

        private final String mTag;

        private Tags(String tag) {
            mTag = tag;
        }

        public String tag() {
            return mTag;
        }

        private static Tags findTag(String tag) {
            for (Tags tags : Tags.values()) {
                if (tag.compareTo(tags.tag()) == 0) {
                    return tags;
                }
            }
            return null;
        }
    }

    /**
     * Create ChatMessage from Hashtable generated by Hessian-decoder.
     * 
     * @param hash Hashtable containing ChatMessage parameters
     */
    private void createFromHashtable(Hashtable<String, Object> hash) {
        LogUtils.logI("ChatMessage.createFromHashtable() hash[" + hash.toString() + "]");
        Enumeration<String> e = hash.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            Tags tag = Tags.findTag(key);
            if (tag != null) {
                setValue(tag, hash.get(key));
            }
        }
    }

    private void setValue(Tags key, Object value) {
        switch (key) {
            case BODY:
                mBody = (String)value;
                break;

            case CONVERSATION_ID:
                mConversationId = (String)value;
                break;

            case FROM:
                mUserId = (String)value;
                break;

            case RECIPIENTS_LIST:
                @SuppressWarnings("unchecked")
                Vector<String> vect = (Vector<String>)value;
                mTos = new ArrayList<String>(vect);
                break;

            default:
                LogUtils.logE("ChatMessage.setValue() key[" + key + "] value[" + value
                        + "] Unsupported KEY");
        }
    }

    public ChatMessage() {
        // Do nothing.
    }

    protected ChatMessage(RpgPushMessage msg) {
        createFromHashtable(msg.mHash);
    }

    @Override
<<<<<<< HEAD
    public int type() {
=======
    public int getType() {
>>>>>>> 5ab21f731a48ab98689573eeae02afa669913d42
        return CHAT_MSG_DATA_TYPE;
    }

    public String getConversationId() {
        return mConversationId;
    }

    public void setConversationId(String conversationId) {
        this.mConversationId = conversationId;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    public List<String> getTos() {
        return mTos;
    }

    public void setTos(List<String> tos) {
        this.mTos = tos;
    }

    public Long getLocalContactId() {
        return mLocalContactId;
    }

    public void setLocalContactId(Long localContactId) {
        this.mLocalContactId = localContactId;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String senderUserId) {
        this.mUserId = senderUserId;
    }

    public int getNetworkId() {
        return mNetworkId;
    }

    public void setNetworkId(int networkId) {
        this.mNetworkId = networkId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chat Message [mBody=");
        sb.append(mBody); 
        sb.append(", mConversationId="); sb.append(mConversationId);
        sb.append(", mLocalContactId="); sb.append(mLocalContactId); 
        sb.append(", mNetworkId=");  sb.append(mNetworkId);
        sb.append(", mUserId="); sb.append(mUserId); 
        sb.append(", mTos="); sb.append(mTos); sb.append("]");
        return sb.toString();
    }
    
    /**
     * This method returns the time when the message was created/sent.
     */
    public long getTimeStamp() {
        return mTimeStamp;
    }

    /**
     * This method sets the time when the message was created/sent into this ChatMessage.
     * @param timeStamp long - the time when the message was created.
     */
    public void setTimeStamp(long timeStamp) {
        this.mTimeStamp = timeStamp;
    }
}
