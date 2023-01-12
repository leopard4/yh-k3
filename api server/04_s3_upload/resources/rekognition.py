from flask import request
from flask_jwt_extended import jwt_required
from flask_restful import Resource
from mysql.connector import Error
from flask_jwt_extended import get_jwt_identity
from mysql_connection import get_connection

from datetime import datetime
import boto3
from config import Config

class ObjectDetectionResource(Resource) :

    # S3에 저장되 있는 이미지를
    # 객체 탐지 하는 API
    def get(self) :

        # 1. 클라이언트로부터 파일명을 받아온다.
        filename = request.args.get('filename')

        # 2. 위의 파일은 이미 S3에 있다는 상황.
        # 따라서 aws의 rekognition 인공지능서비스를
        # 이용해서, object detection 한다.

        # 리코그니션 서비스 이용할수 있는지
        # IAM 의 유저 권한 확인하고, 설정해준다.
        client = boto3.client('rekognition',
                    'ap-northeast-2',
                    aws_access_key_id=Config.ACCESS_KEY,
                    aws_secret_access_key = Config.SECRET_ACCESS)

        response = client.detect_labels(Image={'S3Object':{'Bucket':Config.S3_BUCKET, 'Name':filename}} ,
                            MaxLabels = 10 )

        print(response)

        for label in response['Labels']:
            print ("Label: " + label['Name'])
            print ("Confidence: " + str(label['Confidence']))
            print ("Instances:")
            for instance in label['Instances']:
                print ("  Bounding box")
                print ("    Top: " + str(instance['BoundingBox']['Top']))
                print ("    Left: " + str(instance['BoundingBox']['Left']))
                print ("    Width: " +  str(instance['BoundingBox']['Width']))
                print ("    Height: " +  str(instance['BoundingBox']['Height']))
                print ("  Confidence: " + str(instance['Confidence']))
                print()

            print ("Parents:")
            for parent in label['Parents']:
                print ("   " + parent['Name'])
            print ("----------")
            print ()


        return {'result' : 'success', 
                'Labels' : response['Labels']}, 200





