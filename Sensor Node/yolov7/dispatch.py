import os
from pathlib import Path
import shutil
import requests

# IP address and port of the receiver
with open("URL.txt", "r") as file:
    # Read the first line of the file
    url = file.read().strip()
    print(url)


root_dir = '/home/pi/Desktop/Rat_Detection/Detections/' #Raspberry Path
dest_dir = '/home/pi/Desktop/Rat_Detection/Detections/Backup' #Raspberry BackupPath
#root_dir = r"E:\Cloud_test"
#dest_dir = r"E:\Cloud_test\Backup"
os.makedirs(dest_dir, exist_ok=True)
extensions = ['*.jpg']  # All image file types you want to read

while(True):
    for extension in extensions:
        for file in Path(root_dir).glob(extension):
            try:
                # Open the image file and read its data
                with open(file, 'rb') as f:
                    image_data = bytearray(f.read())
                    # Send a POST request to the URL with the image as the request body
                    # headers = {'content-type': 'image/jpg'}
                    # print(os.path.basename(file))
                    files = {
                        'image': (os.path.basename(file), image_data, 'image/jpg'),
                    }
                    #print(type(file))
                    #print(type(f))
                    response = requests.post(url, files=files)
                    f.close()
                    print(f"sent {file}, Response status: {response.status_code}")
                    # Move images to Backup after sending to cluster                    
                    shutil.move(f.name, dest_dir)
                    # Delete files after sending if needed, current approach to move to Backup folder
                    #os.remove(f.name)
            except Exception as e:
                print(f"Exception occured while sending file: {e}")
                # Close the socket
