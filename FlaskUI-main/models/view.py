#!/usr/bin/sh env python
import requests
import json
from urllib.request import urlopen
from PIL import Image
from io import BytesIO
from flask import request
from flask import render_template, Flask
import os

app = Flask(__name__ , static_folder='../static/',template_folder='../templates/')


@app.route('/')
def index():
	print(os.getcwd())
	return render_template('base.html')
data_json = None
data_json2 = None

@app.route('/rat_page', methods=["GET", "POST"])
def rat():
	url = "http://192.168.0.10:8083/image/list"
	# store the response of URL
	response = urlopen(url)

	# storing the JSON response 
	# from url in data
	data_json = json.loads(response.read())

	img_url = []
	for i in range(0,len(data_json)):
		url1= "http://192.168.0.10:8083/image/"
		a=data_json[i].get("id")
		url1=url1+str(a)
		img_url.append(url1)
		
		imgresponse=requests.get(url1)
		img =Image.open(BytesIO(imgresponse.content))
	
	if request.method == 'POST':
		img.show()


	return render_template('rat_page.html', title="ad",data_json1=data_json, img_urls=img_url)

if __name__ == '__main__':
	app.run(host="0.0.0.0", port=5000)