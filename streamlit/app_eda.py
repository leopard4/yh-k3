import streamlit as st
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sb


def run_eda_app() :

    df = pd.read_csv('data/Car_Purchasing_Data.csv', encoding='ISO-8859-1')

    st.subheader('데이터프레임 확인')
    st.dataframe(df.head(3))

    st.subheader('기본 통계 데이터')
    st.dataframe( df.describe() )

    # 컬럼을 선택할수 있게 한다. 하나의 컬럼을 선택하면,
    # 해당 컬럼의 최대값, 최소값 데이터를 화면에 보여준다.
    st.subheader('최대 / 최소 데이터 확인하기')
    
    column_list = df.columns[4:]

    seleced_column = st.selectbox('컬럼을 선택하세요.', column_list)

    df.loc[ df[seleced_column] ==  df[seleced_column].max() , ]
    df.loc[ df[seleced_column] ==  df[seleced_column].min() , ]











