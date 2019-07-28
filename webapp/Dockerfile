FROM node:10.15.3
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
RUN npm install --save-dev @angular-devkit/build-angular
RUN npm install -g @angular/cli@6.1.0
COPY . /usr/src/app
RUN npm link
CMD ng serve --host 0.0.0.0 --port 4200
