/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.synapse.chatting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author thiennv15
 */
@Entity
@Table(name = "conversation")
@Getter
@Setter
@EqualsAndHashCode(onParam = @__(@Id))
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conversation_id")
    private Long conversationId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "channel_id")
    private String channelId;
    @Column(name = "status")
    private String status;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
    @Column(name = "updated_user")
    private String updatedUser;
    @Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversationId")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversationId")
    private Collection<Participant> participantCollection;

}
