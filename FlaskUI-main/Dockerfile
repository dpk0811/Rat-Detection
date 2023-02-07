FROM python:alpine3.17
WORKDIR /uiapp
COPY . /uiapp
RUN pip install -r requirements.txt
EXPOSE 5000
CMD python ./models/view.py