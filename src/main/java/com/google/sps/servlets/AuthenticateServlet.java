// Copyright 2020 Google LLC
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

package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.sps.data.LoginState;
import com.google.sps.data.PublicAccessPage;
import com.google.sps.util.ErrorMessages;
import com.google.sps.util.ParameterConstants;
import com.google.sps.util.ServletUtils;
import com.google.sps.util.URLPatterns;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet supports HTTP GET and returns a real time summary of the login status of the caller.
 * The response is served in JSON format. This servlet is used by the function in autheticate.js to
 * determine if the user viewing the site should be allowed to continue or redirected to a public
 * page.
 *
 * @author sylviaziyuz
 * @author guptamudit
 * @version 1.0
 * @param URLPatterns.AUTHENTICATE this servlet serves requests at /authenticate
 */
@WebServlet(urlPatterns = URLPatterns.AUTHENTICATE)
public class AuthenticateServlet extends HttpServlet {
  private static final Logger LOG = Logger.getLogger(AuthenticateServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    LoginState loginState = new LoginState();
    UserService userService = UserServiceFactory.getUserService();
    String responseString;
    String redirDest = getRedirPathname(request);
    if (!PublicAccessPage.publicAccessPage.contains(redirDest)) loginState.autoRedir = true;
    if (!userService.isUserLoggedIn()) {
      String redirUrlAfterLogin = redirDest;
      loginState.toggleLoginURL = userService.createLoginURL(redirUrlAfterLogin);
      loginState.isLoggedIn = false;
    } else {
      String redirUrlAfterLogout = URLPatterns.BASE;
      loginState.toggleLoginURL =
          URLPatterns.LOGOUT + "?" + ParameterConstants.REDIR + "=" + (redirUrlAfterLogout);
      loginState.userProfileURL =
          URLPatterns.PROFILE
              + "?"
              + ParameterConstants.USER_ID
              + "="
              + userService.getCurrentUser().getUserId();
      loginState.isLoggedIn = true;
    }
    response.setContentType(ServletUtils.CONTENT_JSON);
    response.getWriter().println(new Gson().toJson(loginState));
  }

  private String getRedirPathname(HttpServletRequest request) {
    String encodedPathname = ServletUtils.getParameter(request, ParameterConstants.REDIR, null);
    if (encodedPathname.equals("")) return URLPatterns.BASE;
    String pathname;
    try {
      pathname = URLDecoder.decode(encodedPathname, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOG.warning(ErrorMessages.badRedirect(encodedPathname));
      return URLPatterns.BASE;
    }
    return pathname;
  }
}
