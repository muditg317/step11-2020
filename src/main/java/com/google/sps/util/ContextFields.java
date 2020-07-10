// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.util;

/**
 * This class stores a list of possible fields for jinja rendering context.
 */
public class ContextFields {
  public static final String URL = "url";
  public static final String IS_LOGGED_IN = "isLoggedIn";
  public static final String IS_MENTOR = "isMentor";
  public static final String IS_MENTEE = "isMentee";
  public static final String CURRENT_USER = "currentUser";
  public static final String FORM_TYPE = "formType";
  public static final String PROFILE_USER = "profileUser";
}
