package com.reaier.engking.domain.recital;

import com.google.gson.annotations.SerializedName;
import com.reaier.engking.constants.Ebbinghaus;
import com.reaier.engking.constants.RecitalPlan;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Builder
/*
* 背诵列表
* */
public class RecitalList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    @Column(name = "user_id")
    private Integer userId;                     //用户主键

    @Column(name = "source_id")
    private Integer sourceId;                   //来源主键

    @Column(name = "word_id")
    private Integer wordId;                     //单记主键

    @Column(name = "plan_time")
    private Date planTime;                      //背诵时间

    @SerializedName("ebbinghaus")
    Ebbinghaus ebbinghaus;                      //背诵曲线

    @SerializedName("next_time")
    @Column(name = "next_time")
    private Date nextTime;                      //下次背诵时间

    @SerializedName("recital_plan")
    @Column(name = "recital_plan")
    private RecitalPlan recitalPlan;            //背诵结果列表

    @SerializedName("result_time")
    @Column(name = "result_time")
    private Date resultTime;                    //背诵结果时间
}
