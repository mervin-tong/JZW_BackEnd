package com.piesat.school.orderfrom.iservice;
<<<<<<< .merge_file_a28788
import com.piesat.school.orderfrom.param.OrderFromAttentionParamData;
import com.piesat.school.orderfrom.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.param.OrderFromParamData;
=======
import com.piesat.school.orderfrom.param.*;
>>>>>>> .merge_file_a72576
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.orderfrom.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IROrderFromService {
    Result<TailPage<OrderFromVTO>> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData);

    Result<OrderFromVTO> orderFromCreate(OrderFromParamData orderFromParamData);

    Result<OrderFromInfoVTO> orderFromInfo(Long orderFromId);

    Result<TailPage<OrderFromAttentionVTO>> attentionList(OrderFromAttentionParamData orderFromAttentionParamData);

<<<<<<< .merge_file_a28788
    //历史下载列表
    Result<TailPage<OrderFromHistoryDownLoadVTO>> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData);
=======
    Result<Boolean> saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);

    Result<Boolean> delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData);
>>>>>>> .merge_file_a72576
}
