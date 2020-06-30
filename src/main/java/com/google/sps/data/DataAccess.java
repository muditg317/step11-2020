package com.google.sps.data;

import com.google.appengine.api.users.User;
import java.util.Collection;

interface DataAccess {

  User getCurrentUser();

  UserAccount getUser(String userID);

  UserAccount getUser(long datastoreKey);

  Mentee getMentee(String userID);

  Mentee getMentee(long datastoreKey);

  Mentor getMentor(String userID);

  Mentor getMentor(long datastoreKey);

  void saveUser(UserAccount user);

  Collection<Mentor> getRelatedMentors(Mentee mentee);

  Collection<Mentee> getRelatedMentees(Mentor mentor);

  Collection<MentorshipRequest> getIncomingRequests(UserAccount user);

  Collection<MentorshipRequest> getOutgoingRequests(UserAccount user);

  void addToShortlist(Mentee mentee, String whichList, Mentor mentor);

  void removeFromShortlist(Mentee mentee, String whichList, Mentor mentor);

  Collection<Mentor> getShortlist(Mentee mentee, String whichList);

  void publishRequest(MentorshipRequest request);

  MentorshipRequest getMentorshipRequest(long requestKey);

  void deleteRequest(MentorshipRequest request);

  // delete request object and create connection object
  void approveRequest(MentorshipRequest request);

  // delete request object
  void denyRequest(MentorshipRequest request);

  void makeConnection(long mentorKey, long menteeKey);

  Collection<Connection> getConnections(UserAccount user);
}
