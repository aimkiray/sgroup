package com.shengdiyage.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shengdiyage.entity.*;
import com.shengdiyage.service.*;
import com.shengdiyage.service.serviceImplement.*;
import com.shengdiyage.utils.DateTools;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServlet extends HttpServlet {

    //    定义全局变量
    private ProductTypeService productTypeService = null;
    private ProductService productService = null;
    private OrderService orderService = null;
    private OrderDetailService orderDetailService = null;
    private CustomerService customerService = null;
    private EmployeeService employeeService = null;

    public OrderServlet() {
        super();
        //    新建产品类别和产品の实现类
        productTypeService = new ProductTypeServiceImpl();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();
        orderDetailService = new OrderDetailServiceImpl();
        customerService = new CustomerServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operate = req.getParameter("operate");
        switch (operate) {
            case "getProductList":
                getProductList(req, resp);
                break;
            case "queryProductById":
                queryProductById(req, resp);
                break;
            case "queryOrderDetail":
                queryOrderDetail(req, resp);
                break;
            case "getEmployeeList":
                getEmployeeList(req, resp);
                break;
            case "getCustomerList":
                getCustomerList(req, resp);
                break;
            case "toOrderList":
                toOrderList(req, resp);
                break;
            case "OrderList":
                OrderList(req, resp);
                break;
            case "toOrderDetail":
                toOrderDetail(req, resp);
                break;
            case "toOrderEdit":
                toOrderEdit(req, resp);
                break;
            case "getOrderJson":
                getOrderJson(req, resp);
                break;
            case "delOrderByOrderId":
                delOrderByOrderId(req, resp);
                break;
            default:
                break;
        }
    }

    /**
     * post方式提交的数据
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 修改订单
     * @param req
     * @param resp
     */
    protected void editOrder(HttpServletRequest req, HttpServletResponse resp) {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int employeeId = Integer.parseInt(req.getParameter("empId"));
        String realDate = req.getParameter("orderDate");
//        如果没传日期，就用当前日期
        Date orderDate = realDate == null ? new Date() : DateTools.getDateByStr(realDate, "yyyy-MM-dd HH:mm:ss");
//        添加Order
        Order order = new Order();
        order.setOrderId(DateTools.getOrderId());
        Customer customer = customerService.queryCustomer(customerId);
        order.setCustomer(customer);
        Employee employee = employeeService.queryEmployee(employeeId);
        order.setEmployee(employee);
        order.setOrderDate(orderDate);
        orderService.editOrder(order);
    }

    /**
     * 通过订单id删除订单
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void delOrderByOrderId(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String orderId = req.getParameter("orderId");
        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);
        int result = 0;
//        int count = 0;
        for(OrderDetail orderDetail : orderDetails) {
//            删除所有订单详情
            result += orderDetailService.delOrderDetail(orderDetail.getId());
        }
        PrintWriter out = resp.getWriter();
//        判断删除还是修改
        String flag = req.getParameter("flag");
//        没抛异常的话
        if (result > -1) {
            if (flag != null && "edit".equals(flag)) {
//                不是删除的话只删除订单详情
                out.print("true");
            } else {
//                若是删除则顺便也删掉订单
                if (orderService.delOrder(orderId) > 0) {
//                    转跳到产品详情
                    OrderList(req, resp);
                } else {
                    out.print("<script>alert('删除失败！');history.back();</script>");
                }
//                resp.sendRedirect("/orderservlet.do?operate=OrderList");
            }
        } else {
            if (flag != null && "edit".equals(flag)) {
                out.print("false");
            } else {
                out.print("<script>alert('删除失败！');history.back();</script>");
            }
        }
//        resp.getWriter().print("true");
    }

    protected void OrderDetail(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("orderId");
        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);
        int result = 0;
//        int count = 0;
        for(OrderDetail orderDetail : orderDetails) {
            result += orderDetailService.delOrderDetail(orderDetail.getId());
        }
    }

    /**
     * 获取已选择产品的数量json格式
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void getOrderJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String orderId = req.getParameter("orderId");

        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);

        Map<String,Integer> resMap = new HashMap<String,Integer>();
        for (OrderDetail orderDetail : orderDetails) {
            resMap.put(Integer.toString(orderDetail.getProduct().getProductId()),orderDetail.getQuantity());
        }
        String jsonMap = JSON.toJSONString(resMap);
        resp.getWriter().print(jsonMap);
    }

    /**
     * 转跳到订单修改页，顺便带上该订单的订单详情和员工顾客
     * @param req
     * @param resp
     */
    protected void toOrderEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");

        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);
        Order order = orderService.queryOrderById(orderId);
        List<Customer> customers = customerService.queryCustomer(0, customerService.queryCustomerNum());
        List<Employee> employees = employeeService.queryEmployee(0, employeeService.queryEmployeeNum());

        /*Map<String,Integer> resMap = new HashMap<String,Integer>();
        for (OrderDetail orderDetail : orderDetails) {
            resMap.put(Integer.toString(orderDetail.getProduct().getProductId()),orderDetail.getQuantity());
        }
        String jsonMap = JSON.toJSONString(resMap);
        System.out.println(jsonMap);*/

//        req.setAttribute("jsonMap", jsonMap);
        req.setAttribute("orderDetails", orderDetails);
        req.setAttribute("order", order);
        req.setAttribute("customers", customers);
        req.setAttribute("employees", employees);

        req.getRequestDispatcher("/order/neworderedit.jsp").forward(req, resp);

    }

    /**
     * 转跳到订单详情页，顺便带上该订单的订单详情
     * @param req
     * @param resp
     */
    protected void toOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
//        System.out.println(orderId);
        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);
        Order order = orderService.queryOrderById(orderId);

        req.setAttribute("orderDetails", orderDetails);
        req.setAttribute("order", order);

        req.getRequestDispatcher("/order/orderdetail.jsp").forward(req, resp);

    }

    /**
     * 将订单和订单详情存入数据库，顺便转跳到订单列表页
     * @param req
     * @param resp
     */
    protected void toOrderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int employeeId = Integer.parseInt(req.getParameter("empId"));
        String realDate = req.getParameter("orderDate");
        Date orderDate = realDate == null ? new Date() : DateTools.getDateByStr(realDate, "yyyy-MM-dd HH:mm:ss");
//        添加Order
        Order order = new Order();
        Customer customer = customerService.queryCustomer(customerId);
        order.setCustomer(customer);
        Employee employee = employeeService.queryEmployee(employeeId);
        order.setEmployee(employee);
        order.setOrderDate(orderDate);
        String flag = req.getParameter("flag");
        if (flag != null && "edit".equals(flag)) {
            String orderId = req.getParameter("orderId");
            order.setOrderId(orderId);
            orderService.editOrder(order);
        } else {
            order.setOrderId(DateTools.getOrderId());
            orderService.addOrder(order);
        }

        String[] discounts = req.getParameterValues("discount");
        String jsonData = req.getParameter("jsonData");
//        jsonData = jsonData.substring(1,jsonData.length()-1);
//        JSON转产品对象列表
        List<Product> products = JSON.parseArray(jsonData, Product.class);
        int mark = 0;
//        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        for(Product product : products) {
//            往订单详情中加值
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getOrderId());
//            Product realProduct = productService.queryProduct(product.getProductId());
            orderDetail.setProduct(product);
            orderDetail.setQuantity(product.getQuantity());
            orderDetail.setDiscount(Integer.parseInt(discounts[mark]));
//            orderDetails.add(orderDetail);
            orderDetailService.addOrderDetail(orderDetail);
            mark++;
        }

////        查询客户和员工名称
//        Customer customer = customerService.queryCustomer(customerId);
//        Employee employee = employeeService.queryEmployee(employeeId);
//        查询订单列表
//        List<Order> orders = orderService.queryOrder(0, orderService.queryOrderNum());

//        req.setAttribute("orders", orders);
//        req.setAttribute("orderDetails", orderDetails);
//        req.setAttribute("customer", customer);
//        req.setAttribute("employee", employee);

//        req.getRequestDispatcher("/order/orderlist.jsp").forward(req, resp);
        OrderList(req, resp);
    }

    /**
     * 查询全部order，转跳到orderlist
     * @param req
     * @param resp
     */
    protected void OrderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        查询订单列表
        List<Order> orders = orderService.queryOrder(0, orderService.queryOrderNum());

        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/order/orderlist.jsp").forward(req, resp);
    }

    /**
     * 获取客户列表
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void getCustomerList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CustomerService customerService = new CustomerServiceImpl();
        List<Customer> customers = customerService.queryCustomer(0, customerService.queryCustomerNum());
        resp.getWriter().print(JSON.toJSONString(customers));
    }

    /**
     * 获取员工列表
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void getEmployeeList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.queryEmployee(0,employeeService.queryEmployeeNum());
        resp.getWriter().print(JSON.toJSONString(employees));
    }

    /**
     * (ajax)获取产品添加界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int productId = Integer.parseInt(req.getParameter("productId"));
//        Product product = productService.queryProduct(productId);
//        List<ProductType> productTypes = productTypeService.queryAllProductType();
//        String strTypes = JSON.toJSONString(productTypes);
//        req.setAttribute("productTypes",productTypes);
//        req.setAttribute("product",product);
        req.getRequestDispatcher("/order/order-add-product.jsp").forward(req, resp);
    }

    protected void queryProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.queryProduct(productId);
        String strProduct = JSON.toJSONString(product);
        resp.getWriter().print(strProduct);
    }

    @Test
    protected void queryOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String realProductMap = req.getParameter("productMap");
//        String productMap = realProductMap == null ? "" : realProductMap;
        // 用map接住js{产品：数量}对象
        Map<String,Integer> resMap = (Map<String,Integer>)JSON.parse(realProductMap);
        JSONObject jsonProduct = new JSONObject();
        String allProducts = "";
        int flag = 0;
        for(String key : resMap.keySet()) {
            if(resMap.get(key) != 0) {
                jsonProduct = (JSONObject) JSON.toJSON(productService.queryProduct(Integer.parseInt(key)));
                jsonProduct.put("quantity",resMap.get(key));
                if(flag == 0) {
                    allProducts += JSON.toJSONString(jsonProduct);
                } else {
                    allProducts += "," + JSON.toJSONString(jsonProduct);
                }
                flag ++;
            }
        }
//        手动拼接JSON字符串,{"rows":\"total\":"+flag+"
        allProducts = "[" + allProducts + "]";
//        System.out.println(allProducts);
        resp.getWriter().print(allProducts);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
