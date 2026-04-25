importScripts('https://www.gstatic.com/firebasejs/9.22.2/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.22.2/firebase-messaging-compat.js');

firebase.initializeApp({
  apiKey: "AIzaSyDxB4eQyeSRoUw-NQpRz9pyGxBTPs1k2X4",
  authDomain: "smartdoubtforum.firebaseapp.com",
  projectId: "smartdoubtforum",
  messagingSenderId: "692220964379",
  appId: "1:692220964379:web:a761d3e9393efb593452be"
});

const messaging = firebase.messaging();

messaging.onBackgroundMessage(function(payload) {
  self.registration.showNotification(payload.notification.title, {
    body: payload.notification.body
  });
});