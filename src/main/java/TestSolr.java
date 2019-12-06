import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolr {
    @Test
    public void testIndexCreateAndUpdate() throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.64.147:8080/solr");
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //添加
/*        document.addField("id","002");
        document.addField("name","后裔");
        document.addField("price",900);*/
        //修改
        document.setField("id","001");
        document.setField("name","刘备");
        document.setField("price",800);

        //添加或者修改
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    //删除
    @Test
    public void testIndexDelete() throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.64.147:8080/solr");
        solrServer.deleteById("002");
        //删除所有
        /*solrServer.deleteByQuery("*:*");*/
        solrServer.commit();
    }

    //查询
    @Test
    public void testIndexSelect() throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.64.147:8080/solr");
        //创建查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");
        //查询并返回响应
        QueryResponse queryResponse = solrServer.query(query);
        //从响应中获取结果集
        SolrDocumentList results = queryResponse.getResults();
        System.out.println("一共有："+results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println("id:"+result.get("id"));
            System.out.println("name:"+result.get("name"));
        }


    }

}
