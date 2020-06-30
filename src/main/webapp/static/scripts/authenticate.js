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

function loadAuthButton() {
  const request = '/authenticate?redir=' + encodeURIComponent(window.location.pathname);
  console.log(request);
  fetch(request).then(handleFetchErrors).then(response => response.json())
    .then(loginState => {
      console.log("Rendering auth button links");
      const authButtonList = document.getElementsByClassName("auth-button");
      for (let i = 0; i < authButtonList.length; i++) {
        let authButtonElem = authButtonList[i];
        console.log(authButtonElem);
        if (loginState.isLoggedIn)
        authButtonElem.innerText = "Log Out";
        else {
          console.log("changine display")
          authButtonElem.innerText = "Log In";
        }
        console.log(loginState.toggleLoginURL);
        authButtonElem.onclick =
          (event) => { window.location = loginState.toggleLoginURL; };
      }
      
    })
}

function handleFetchErrors(response) {
  if (!response.ok) throw Error(response.statusText);
  return response;
}